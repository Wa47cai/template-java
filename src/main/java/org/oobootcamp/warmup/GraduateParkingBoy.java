package org.oobootcamp.warmup;

import org.oobootcamp.warmup.domain.Car;
import org.oobootcamp.warmup.domain.Ticket;
import org.oobootcamp.warmup.exception.AllParkingLotsFullException;
import org.oobootcamp.warmup.exception.CarNotFoundInAllParkingLotsException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Super Idol Pair B
 * @date 2022/5/27
 */
public class GraduateParkingBoy {

    private final List<ParkingLot> orderedParkingLots = new ArrayList<>();

    public void addParkingLot(ParkingLot parkingLot) {
        orderedParkingLots.add(parkingLot);
    }

    public Ticket park(Car car) {
        for (ParkingLot parkingLot : orderedParkingLots) {
            if (!parkingLot.isParkingLotFull()) {
                return parkingLot.park(car);
            }
        }

        throw new AllParkingLotsFullException();
    }

    public Car pickUp(Ticket ticket) {
        for (ParkingLot parkingLot : orderedParkingLots) {
            if (parkingLot.isCarIn(ticket.getCarNumber())) {
                return parkingLot.pickUp(ticket);
            }
        }

        throw new CarNotFoundInAllParkingLotsException();
    }
}
