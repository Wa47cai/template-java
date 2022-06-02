package org.oobootcamp.warmup;

import org.oobootcamp.warmup.domain.Car;
import org.oobootcamp.warmup.domain.Ticket;
import org.oobootcamp.warmup.exception.AllParkingLotsFullException;
import org.oobootcamp.warmup.exception.InvalidTicketException;

import java.util.List;

/**
 * @author Super Idol Pair B
 * @date 2022/5/27
 */
public class GraduateParkingBoy extends ParkingBoy {
    public GraduateParkingBoy(List<ParkingLot> orderedParkingLots) {
        super(orderedParkingLots);
    }

    @Override
    public Ticket park(Car car) {
        for (ParkingLot parkingLot : orderedParkingLots) {
            if (!parkingLot.isFull()) {
                return parkingLot.park(car);
            }
        }

        throw new AllParkingLotsFullException();
    }
}
