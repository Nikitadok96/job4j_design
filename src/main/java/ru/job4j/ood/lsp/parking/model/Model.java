package ru.job4j.ood.lsp.parking.model;

public enum Model {
    Passenger(1),
    Truck(2);

    private final int capacity;

    private Model(int capacity) {
        this.capacity = capacity;
    }

    public int getValue() {
        return capacity;
    }
}
