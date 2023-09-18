package ru.job4j.ood.lsp.parking.controller;

import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.Model;
import ru.job4j.ood.lsp.parking.store.AbstractParkingSpace;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ControlCar {
    private List<AbstractParkingSpace> parkingSpaces = new ArrayList<>();

    public ControlCar(List<AbstractParkingSpace> spaces) {
        this.parkingSpaces.addAll(spaces);
    }

    public boolean add(Car car) {
        boolean rsl = false;
        for (AbstractParkingSpace space : parkingSpaces) {
            if (space.add(car)) {
                rsl = true;
                break;
            } else if (car.getCapacity() > Model.Passenger.getValue()) {
                List<AbstractParkingSpace> closeSpaces = closeSpace(car);
                if (closeSpaces != null) {
                    closeSpaces.forEach(s -> s.setCurrentCar(car));
                    rsl = true;
                }
            }
        }
        return rsl;
    }

    private List<AbstractParkingSpace> closeSpace(Car car) {
        List<AbstractParkingSpace> findList = new ArrayList<>();
        int needSpaces = car.getCapacity();
        int currentSequence = 0;
        for (AbstractParkingSpace space : parkingSpaces) {
            if (currentSequence == needSpaces) {
                break;
            }
            if (space.getCurrentCar() == null) {
                findList.add(space);
                currentSequence++;
            } else {
                findList = new ArrayList<>();
                currentSequence = 0;
            }
        }
        if (findList.size() != needSpaces) {
            findList = null;
        }
        return findList;
    }
}
