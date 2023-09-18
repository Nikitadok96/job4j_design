package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.Model;

public class TruckSpace extends AbstractParkingSpace {
    @Override
    public boolean getSpaceCondition(Car currentCar) {
        return currentCar.getCapacity() == Model.Truck.getValue();
    }
}
