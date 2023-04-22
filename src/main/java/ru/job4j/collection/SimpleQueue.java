package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    ForwardLinked<T> forwardLinked = new ForwardLinked<>();

    public T poll() {
        T el;
        try {
            el = forwardLinked.deleteFirst();
            in.push(el);
            out.push(in.pop());
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Queue is empty");
        }
        return out.pop();
    }

    public void push(T value) {
        forwardLinked.add(value);
    }
}
