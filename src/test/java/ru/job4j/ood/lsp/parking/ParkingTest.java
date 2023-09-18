package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.PassengerCar;
import ru.job4j.ood.lsp.parking.model.TruckCar;
import ru.job4j.ood.lsp.parking.store.AbstractParkingSpace;
import ru.job4j.ood.lsp.parking.store.PassengerSpace;
import ru.job4j.ood.lsp.parking.store.TruckSpace;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Disabled
class ParkingTest {
    @Test
    public void whenCurrentAdd1Pas1Truck() {
        Car passengerCar = new PassengerCar("303-595", 1);
        Car truckCar = new TruckCar("102-493", 2);
        AbstractParkingSpace passengerSpace = new PassengerSpace();
        AbstractParkingSpace truckSpace = new TruckSpace();
        Parking parkingSpace = new Parking(List.of(passengerSpace, truckSpace));
        parkingSpace.tryAddCar(passengerCar);
        parkingSpace.tryAddCar(truckCar);
        Car rslPasCar = passengerSpace.getCurrentCar();
        assertThat(rslPasCar).isEqualTo(passengerCar);
        Car rslTruckCar = truckSpace.getCurrentCar();
        assertThat(rslTruckCar).isEqualTo(truckCar);
    }

    @Test
    public void whenTryAdd1Pas1TruckIn2PasSpace() {
        Car passengerCar = new PassengerCar("303-595", 1);
        Car truckCar = new TruckCar("102-493", 2);
        AbstractParkingSpace firstPassengerSpace = new PassengerSpace();
        AbstractParkingSpace secondPassengerSpace = new PassengerSpace();
        Parking parkingSpace = new Parking(List.of(firstPassengerSpace, secondPassengerSpace));
        parkingSpace.tryAddCar(passengerCar);
        parkingSpace.tryAddCar(truckCar);
        Car firstRslPasCar = firstPassengerSpace.getCurrentCar();
        assertThat(firstRslPasCar).isEqualTo(passengerCar);
        Car secondRslPasCar = secondPassengerSpace.getCurrentCar();
        assertThat(secondRslPasCar).isNull();
    }

    @Test
    public void whenTryAdd2PasIn2TruckSpace() {
        Car firstPassengerCar = new PassengerCar("303-595", 1);
        Car secondPassengerCar = new PassengerCar("102-493", 1);
        AbstractParkingSpace firstTruckSpace = new TruckSpace();
        AbstractParkingSpace secondTruckSpace = new TruckSpace();
        Parking parkingSpace = new Parking(List.of(firstTruckSpace, secondTruckSpace));
        parkingSpace.tryAddCar(firstPassengerCar);
        parkingSpace.tryAddCar(secondPassengerCar);
        Car firstRsl = firstTruckSpace.getCurrentCar();
        Car secondRsl = firstTruckSpace.getCurrentCar();
        assertThat(firstRsl).isNull();
        assertThat(secondRsl).isNull();
    }

    @Test
    public void whenCurrentAdd1TruckIn2ClosePassengerSpace() {
        Car truck = new TruckCar("928-943", 2);
        AbstractParkingSpace firstPassengerSpace = new PassengerSpace();
        AbstractParkingSpace secondPassengerSpace = new PassengerSpace();
        Parking parkingSpace = new Parking(List.of(firstPassengerSpace, secondPassengerSpace));
        boolean isAdded = parkingSpace.tryAddCar(truck);
        assertThat(isAdded).isTrue();
        Car rslFirst = firstPassengerSpace.getCurrentCar();
        Car rslSecond = secondPassengerSpace.getCurrentCar();
        assertThat(rslFirst).isEqualTo(truck);
        assertThat(rslSecond).isEqualTo(truck);
    }

    @Test
    public void whenDeleteCarInSpace() {
        Car firstPassenger = new PassengerCar("928-943", 1);
        AbstractParkingSpace firstPassengerSpace = new PassengerSpace();
        Parking parkingSpace = new Parking(List.of(firstPassengerSpace));
        parkingSpace.tryAddCar(firstPassenger);
        assertThat(firstPassengerSpace.getCurrentCar()).isEqualTo(firstPassenger);
        parkingSpace.delete(firstPassenger);
        assertThat(firstPassengerSpace.getCurrentCar()).isNull();
    }

    @Test
    public void whenNotAdded1TruckIn2NotClosePassengerSpace() {
        Car truck = new TruckCar("428-943", 2);
        Car firstPassenger = new PassengerCar("928-655", 1);
        Car secondPassenger = new PassengerCar("340-943", 1);
        AbstractParkingSpace firstPassengerSpace = new PassengerSpace();
        AbstractParkingSpace secondPassengerSpace = new PassengerSpace();
        AbstractParkingSpace thirdPassengerSpace = new PassengerSpace();
        Parking parkingSpace = new Parking(
                List.of(firstPassengerSpace,
                        secondPassengerSpace,
                        thirdPassengerSpace));
        parkingSpace.tryAddCar(firstPassenger);
        parkingSpace.tryAddCar(secondPassenger);
        parkingSpace.delete(firstPassenger);
        boolean isAdded = parkingSpace.tryAddCar(truck);
        assertThat(isAdded).isFalse();
        assertThat(firstPassengerSpace.getCurrentCar()).isNull();
        assertThat(thirdPassengerSpace.getCurrentCar()).isNull();
    }
}