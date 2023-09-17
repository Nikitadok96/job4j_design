package ru.job4j.ood.lsp.parking.controller;

import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.store.AbstractParkingSpace;

import java.util.ArrayList;
import java.util.List;

public class ControlCar {
    private List<AbstractParkingSpace> parkingSpaces = new ArrayList<>();

    public ControlCar(List<AbstractParkingSpace> spaces) {
        this.parkingSpaces.addAll(spaces);
    }

    public void init(List<Car> cars) {
        parkingSpaces.forEach(space -> {
            cars.removeIf(space::add);
        });
    }

    public List<AbstractParkingSpace> getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(List<AbstractParkingSpace> parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }
}
