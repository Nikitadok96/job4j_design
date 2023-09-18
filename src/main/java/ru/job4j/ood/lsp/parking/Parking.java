package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.controller.ControlCar;
import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.store.AbstractParkingSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Parking {
    private final List<AbstractParkingSpace> parkingSpaces;
    private final ControlCar controlCar;

    public Parking(List<AbstractParkingSpace> parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
        controlCar = new ControlCar(parkingSpaces);
    }

    public boolean tryAddCar(Car car) {
        return controlCar.add(car);
    }

    public boolean delete(Car car) {
        boolean rsl = false;
        List<AbstractParkingSpace> spaces = findSpaces(car);
        if (!spaces.isEmpty()) {
           spaces.forEach(s -> s.setCurrentCar(null));
           rsl = true;
        }
        return rsl;
    }

    private List<AbstractParkingSpace> findSpaces(Car car) {
        List<AbstractParkingSpace> spaces = new ArrayList<>();
        parkingSpaces.forEach(s -> {
            if (car.equals(s.getCurrentCar())) {
                spaces.add(s);
            }
        });
        return spaces;
    }

}
