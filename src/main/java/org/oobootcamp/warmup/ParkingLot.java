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
    private final Set<Car> parkedCars = new HashSet<>();

    ParkingLot(int parkingSpaceCapacity) {
        this.parkingSpaceCapacity = parkingSpaceCapacity;
    }

    public Ticket park(Car car) {
        if (isFull()) {
            throw new ParkingLotFullException();
        }

        Ticket ticket = new Ticket();
        ticket.setCarNumber(car.getCarNumber());
        parkedCars.add(car);
        return ticket;
    }

    public Car pickUp(Ticket ticket) {
        if (!isCarIn(ticket)) {
            throw new InvalidTicketException();
        }
        Car car = parkedCars.stream()
                .filter(parkedCar -> parkedCar.getCarNumber().equals(ticket.getCarNumber()))
                .findAny().get();
        parkedCars.remove(car);
        return car;
    }

    public boolean isFull() {
        return parkedCars.size() >= parkingSpaceCapacity;
    }

    public boolean isCarIn(Ticket ticket) {
        return parkedCars.stream().anyMatch(car -> car.getCarNumber().equals(ticket.getCarNumber()));
    }

    public int getFreeSpaceCount() {
        return parkingSpaceCapacity - parkedCars.size();
    }
}
