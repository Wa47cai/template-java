package org.oobootcamp.warmup;

import org.oobootcamp.warmup.domain.Car;
import org.oobootcamp.warmup.domain.Ticket;
import org.oobootcamp.warmup.exception.AllParkingLotsFullException;
import org.oobootcamp.warmup.exception.InvalidTicketException;

import java.util.List;

/**
 * @author Super Idol Pair B
 * @date 2022/6/2
 */
public class ParkingManager {
    List<Parkable> parkables;

    public ParkingManager(List<Parkable> parkables) {
        this.parkables = parkables;
    }

    public Ticket park(Car car) {
        for (Parkable parkable : parkables) {
            if (!parkable.isFull()) {
                return parkable.park(car);
            }
        }

        throw new AllParkingLotsFullException();
    }

    public Car pickUp(Ticket ticket) {
        for (Parkable parkable : parkables) {
            if (parkable.isCarIn(ticket)) {
                return parkable.pickUp(ticket);
            }
        }
        throw new InvalidTicketException();
    }
}
