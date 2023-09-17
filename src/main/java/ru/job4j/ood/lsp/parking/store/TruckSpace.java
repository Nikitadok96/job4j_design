package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Car;

public class TruckSpace extends AbstractParkingSpace {
    @Override
    public boolean add(Car car) {
        return false;
    }
}
