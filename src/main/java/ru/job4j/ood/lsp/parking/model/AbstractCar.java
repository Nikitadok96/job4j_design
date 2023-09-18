package ru.job4j.ood.lsp.parking.model;

import ru.job4j.ood.lsp.parking.store.AbstractParkingSpace;

import java.util.Objects;

public abstract class AbstractCar implements Car {
    private final String number;
    private final int capacity;

    public AbstractCar(String number, int capacity) {
        this.capacity = capacity;
        this.number = number;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractCar that = (AbstractCar) o;
        return capacity == that.capacity && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, capacity);
    }
}
