package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int count;
    public T poll() {
        if (count > 0) {
            for (int i = 0; i < count - 1; i++) {
                out.push(in.pop());
            }
            T element = in.pop();
            if (count == 1) {
                count = 0;
            } else {
                for (int i = 0; i < count - 1; i++) {
                    in.push(out.pop());
                }
                count--;
            }
            return element;
        }
        throw new NoSuchElementException("Queue is empty");
    }

    public void push(T value) {
        in.push(value);
        count++;
    }
}
