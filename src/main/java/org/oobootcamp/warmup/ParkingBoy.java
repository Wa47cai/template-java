package org.oobootcamp.warmup;

import org.oobootcamp.warmup.domain.Car;
import org.oobootcamp.warmup.domain.Ticket;
import org.oobootcamp.warmup.exception.InvalidTicketException;

import java.util.List;

/**
 * @author Super Idol Pair B
 * @date 2022/6/2
 */
public abstract class ParkingBoy {
    protected List<ParkingLot> orderedParkingLots;

    public ParkingBoy(List<ParkingLot> orderedParkingLots) {
        this.orderedParkingLots = orderedParkingLots;
    }

    public abstract Ticket park(Car car);

    public Car pickUp(Ticket ticket) {
        for (ParkingLot parkingLot : orderedParkingLots) {
            if (parkingLot.isCarIn(ticket)) {
                return parkingLot.pickUp(ticket);
            }
        }

        throw new InvalidTicketException();
    }
}
