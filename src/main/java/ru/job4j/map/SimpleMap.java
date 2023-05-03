package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (key != null) {
            int h = hash(key.hashCode());
            int index = indexFor(h);
            if (table[index] == null) {
                table[index] = new MapEntry<>(key, value);
                count++;
                modCount++;
                rsl = true;
            }
        } else {
            if (table[0] == null) {
                table[0] = new MapEntry<>(null, value);
                count++;
                modCount++;
                rsl = true;
            }
        }
        if (count >= LOAD_FACTOR * capacity) {
            expand();
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return (hashCode) ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        int newCapacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[newCapacity];
        MapEntry<K, V>[] oldTable = new MapEntry[capacity];
        System.arraycopy(table, 0, oldTable, 0, table.length);
        capacity *= 2;
        for (int i = 0; i < oldTable.length; i++) {
            MapEntry<K, V> e = oldTable[i];
            K key = e == null ? null : e.key;
            if (key != null) {
                int h = hash(key.hashCode());
                int index = indexFor(h);
                newTable[index] = new MapEntry<>(key, e.value);
            } else {
                newTable[0] = new MapEntry<>(null, get(null));
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V value = null;
        if (key != null) {
            int h = hash(key.hashCode());
            int index = indexFor(h);
            if (table[index] != null) {
                MapEntry<K, V> old = table[index];
                if (old.key != null) {
                    int oldHash = hash(old.key.hashCode());
                    if (oldHash == h && key.equals(old.key)) {
                        value = table[index].value;
                    }
                }
            }
        } else {
            MapEntry<K, V> m = table[0];
            if (m != null && m.key == null) {
                value = table[0].value;
            }
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rls = false;
        if (key != null) {
            int h = hash(key.hashCode());
            int index = indexFor(h);
            if (table[index] != null) {
                K old = table[index].key;
                int oldHash = hash(old.hashCode());
                if (oldHash == h && key.equals(old)) {
                    table[index].key = null;
                    table[index].value = null;
                    table[index] = null;
                    modCount++;
                    count--;
                    rls = true;
                }
            }
        } else {
            MapEntry<K, V> m = table[0];
            if (m != null) {
                table[0].key = null;
                table[0].value = null;
                table[0] = null;
                modCount++;
                count--;
                rls = true;
            }
        }
        return rls;
    }

    @Override
    public Iterator<K> iterator() {
        int expectedModCount = modCount;
        MapEntry<K, V>[] t = table;
        return new Iterator<K>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < t.length && t[index] == null) {
                    index++;
                }
                return index < t.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return t[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
