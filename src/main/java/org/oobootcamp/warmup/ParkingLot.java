package org.oobootcamp.warmup;

import org.oobootcamp.warmup.domain.Car;
import org.oobootcamp.warmup.domain.Ticket;
import org.oobootcamp.warmup.exception.InvalidTicketException;
import org.oobootcamp.warmup.exception.ParkingLotFullException;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Super Idol Pair B
 * @date 2022/5/25
 */
public class ParkingLot {
    private final int parkingSpaceCapacity;
    private final Set<String> parkedCarNumbers = new HashSet<>();

    ParkingLot(int parkingSpaceCapacity) {
        this.parkingSpaceCapacity = parkingSpaceCapacity;
    }

    public Ticket park(Car car) {
        if (isFull()) {
            throw new ParkingLotFullException();
        }

        Ticket ticket = new Ticket();
        ticket.setCarNumber(car.getCarNumber());
        parkedCarNumbers.add(car.getCarNumber());
        return ticket;
    }

    public Car pickUp(Ticket ticket) {
        if (!parkedCarNumbers.contains(ticket.getCarNumber())) {
            throw new InvalidTicketException();
        }
        Car car = new Car(ticket.getCarNumber());
        parkedCarNumbers.removeIf((parkedCarNumber) -> parkedCarNumber.equals(ticket.getCarNumber()));
        return car;
    }

    public boolean isFull() {
        return parkedCarNumbers.size() >= parkingSpaceCapacity;
    }


    public boolean isCarIn(String carNumber) {
        return parkedCarNumbers.contains(carNumber);
    }
}
