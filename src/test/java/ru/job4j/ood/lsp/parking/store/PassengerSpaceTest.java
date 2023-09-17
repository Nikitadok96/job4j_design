package ru.job4j.ood.lsp.parking.store;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.PassengerCar;
import ru.job4j.ood.lsp.parking.model.TruckCar;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Disabled
class PassengerSpaceTest {

    @Test
    public void whenCurrentAddOnePassengerCar() {
        AbstractParkingSpace abstractParkingSpace = new PassengerSpace();
        Car car = new PassengerCar("505-404", 1);
        boolean isAdded = abstractParkingSpace.add(car);
        assertThat(isAdded).isTrue();
        Car rsl = abstractParkingSpace.getCurrentCar();
        assertThat(car).isEqualTo(rsl);
    }

    @Test
    public void whenNotAddOneTruckCar() {
        AbstractParkingSpace abstractParkingSpace = new PassengerSpace();
        Car car = new TruckCar("303-202", 2);
        boolean isAdded = abstractParkingSpace.add(car);
        assertThat(isAdded).isFalse();
        Car rsl = abstractParkingSpace.getCurrentCar();
        assertThat(rsl).isNull();
    }

    @Test
    public void whenTryAddCarToBusyLot() {
        AbstractParkingSpace abstractParkingSpace = new PassengerSpace();
        Car firstCar = new PassengerCar("303-202", 1);
        Car secondCar = new PassengerCar("505-202", 1);
        abstractParkingSpace.add(firstCar);
        boolean isAddedSecondCar = abstractParkingSpace.add(secondCar);
        assertThat(isAddedSecondCar).isFalse();
        Car rsl = abstractParkingSpace.getCurrentCar();
        assertThat(firstCar).isEqualTo(rsl);
    }
}