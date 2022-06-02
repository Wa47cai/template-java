package org.oobootcamp.warmup;

import org.oobootcamp.warmup.domain.Car;
import org.oobootcamp.warmup.domain.Ticket;
import org.oobootcamp.warmup.exception.AllParkingLotsFullException;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy{

    public SmartParkingBoy(List<ParkingLot> orderedParkingLots) {
        super(orderedParkingLots);
    }

    @Override
    public Ticket park(Car car) {
        ParkingLot maxFreeSpaceParkingLot = orderedParkingLots.get(0);
        for (ParkingLot parkingLot : orderedParkingLots) {
            if (parkingLot.getFreeSpaceCount() > maxFreeSpaceParkingLot.getFreeSpaceCount()) {
                maxFreeSpaceParkingLot = parkingLot;
            }
        }
        if (maxFreeSpaceParkingLot.isFull()) {
            throw new AllParkingLotsFullException();
        }
        return maxFreeSpaceParkingLot.park(car);
    }
}
