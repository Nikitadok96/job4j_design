package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Car;

public abstract class AbstractParkingSpace {
    private Car currentCar;
    public abstract boolean add(Car car);

    public Car getCurrentCar() {
        return this.currentCar;
    }
}
