package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Car;

public abstract class AbstractParkingSpace {
    private Car currentCar;
    public boolean add(Car car) {
        boolean rsl = false;
        if (currentCar == null && getSpaceCondition(car)) {
            currentCar = car;
            rsl = true;
        }
        return rsl;
    }

    public Car getCurrentCar() {
        return this.currentCar;
    }

    public void setCurrentCar(Car car) {
        this.currentCar = car;
    }

    public abstract boolean getSpaceCondition(Car currentCar);
}
