package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index = -1;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int i = index + 1; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[getIndex()];
    }

    private int getIndex() {
        for (int i = index + 1; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                index = i;
                return index;
            }
        }
        return -1;
    }
}
