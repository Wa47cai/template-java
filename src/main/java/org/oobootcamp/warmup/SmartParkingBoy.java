package org.oobootcamp.warmup;

import org.oobootcamp.warmup.domain.Car;
import org.oobootcamp.warmup.domain.Ticket;
import org.oobootcamp.warmup.exception.AllParkingLotsFullException;
import org.oobootcamp.warmup.exception.InvalidTicketException;

import java.util.List;

public class SmartParkingBoy {
    private final List<ParkingLot> orderedParkingLots;

    public SmartParkingBoy(List<ParkingLot> orderedParkingLots) {
        this.orderedParkingLots = orderedParkingLots;

    }

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

    public Car pickUp(Ticket ticket) {
        for (ParkingLot parkingLot : orderedParkingLots) {
            if (parkingLot.isCarIn(ticket)) {
                return parkingLot.pickUp(ticket);
            }
        }

        throw new InvalidTicketException();
    }
}
