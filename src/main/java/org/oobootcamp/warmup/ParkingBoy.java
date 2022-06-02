package org.oobootcamp.warmup;

import org.oobootcamp.warmup.domain.Car;
import org.oobootcamp.warmup.domain.Ticket;
import org.oobootcamp.warmup.exception.InvalidTicketException;

import java.util.List;

/**
 * @author Super Idol Pair B
 * @date 2022/6/2
 */
public abstract class ParkingBoy implements Parkable {
    protected List<ParkingLot> orderedParkingLots;

    public ParkingBoy(List<ParkingLot> orderedParkingLots) {
        this.orderedParkingLots = orderedParkingLots;
    }

    @Override
    public abstract Ticket park(Car car);

    @Override
    public Car pickUp(Ticket ticket) {
        for (ParkingLot parkingLot : orderedParkingLots) {
            if (parkingLot.isCarIn(ticket)) {
                return parkingLot.pickUp(ticket);
            }
        }

        throw new InvalidTicketException();
    }

    @Override
    public boolean isCarIn(Ticket ticket) {
        for (ParkingLot parkingLot : orderedParkingLots) {
            if (parkingLot.isCarIn(ticket)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isFull() {
        for (ParkingLot parkingLot : orderedParkingLots) {
            if (!parkingLot.isFull()) {
                return false;
            }
        }

        return true;
    }
}
