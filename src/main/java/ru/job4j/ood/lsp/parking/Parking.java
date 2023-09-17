package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.controller.ControlCar;
import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.store.AbstractParkingSpace;

import java.util.List;

public class Parking {
    private final List<AbstractParkingSpace> parkingSpaces;
    private final ControlCar controlCar;

    public Parking(List<AbstractParkingSpace> parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
        controlCar = new ControlCar(parkingSpaces);
    }

    public List<AbstractParkingSpace> getParkingSpaces() {
        return parkingSpaces;
    }

    public boolean init(Car car) {
        return false;
    }

    public boolean delete(Car car) {
        return false;
    }
}
