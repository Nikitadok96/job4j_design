package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.Model;

public class PassengerSpace extends AbstractParkingSpace {
    @Override
    public boolean getSpaceCondition(Car currentCar) {
        return currentCar.getCapacity() == Model.Passenger.getValue();
    }
}
