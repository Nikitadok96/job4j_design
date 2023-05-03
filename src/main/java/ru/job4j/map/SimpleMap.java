package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private boolean putVal(K key, V value, int index) {
        table[index] = new MapEntry<>(key, value);
        count++;
        modCount++;
        return true;
    }

    private int getIndex(K key) {
        int h = hash(key.hashCode());
        return indexFor(h);
    }

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        int index = Objects.nonNull(key) ? getIndex(key) : -1;
        if (index != -1) {
            if (Objects.isNull(table[index])) {
                rsl = putVal(key, value, index);
            }
        } else  {
            if (Objects.isNull(table[0])) {
                rsl = putVal(null, value, 0);
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
        MapEntry<K, V>[] oldTable = new MapEntry[capacity];
        System.arraycopy(table, 0, oldTable, 0, table.length);
        capacity *= 2;
        table = new MapEntry[capacity];
        for (int i = 0; i < oldTable.length; i++) {
            MapEntry<K, V> e = oldTable[i];
            K key = e == null ? null : e.key;
            if (Objects.nonNull(key)) {
                int index = getIndex(key);
                table[index] = new MapEntry<>(key, e.value);
            } else {
                table[0] = new MapEntry<>(null, get(null));
            }
        }
    }

    @Override
    public V get(K key) {
        V value = null;
        int index = Objects.nonNull(key) ? getIndex(key) : -1;
        if (index != -1) {
            if (Objects.nonNull(table[index])) {
                MapEntry<K, V> old = table[index];
                if (Objects.nonNull(old.key)) {
                    if (hash(old.key.hashCode()) == hash(key.hashCode()) && key.equals(old.key)) {
                        value = table[index].value;
                    }
                }
            }
        } else {
            if (Objects.nonNull(table[0]) && Objects.isNull(table[0].key)) {
                value = table[0].value;
            }
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rls = false;
        int index = Objects.nonNull(key) ? getIndex(key) : -1;
        if (index != -1 && Objects.nonNull(table[index])) {
            K old = table[index].key;
            if (hash(old.hashCode()) == hash(key.hashCode()) && key.equals(old)) {
                rls = removeVal(index);
            }
        } else {
            if (Objects.nonNull(table[0])) {
                rls = removeVal(0);
            }
        }
        return rls;
    }

    private boolean removeVal(int index) {
        table[index].key = null;
        table[index].value = null;
        table[index] = null;
        modCount++;
        count--;
        return true;
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
