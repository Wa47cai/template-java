package org.oobootcamp.warmup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oobootcamp.warmup.domain.Car;
import org.oobootcamp.warmup.domain.Ticket;
import org.oobootcamp.warmup.exception.InvalidTicketException;
import org.oobootcamp.warmup.exception.ParkingLotFullException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author Super Idol Pair B
 * @date 2022/5/25
 */
public class ParkingLotTest {
    private ParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot(1);
    }

    @Test
    void should_succeed_to_park_when_park_car_given_one_car_and_not_full_parking_lot() {
        Car car = new Car("鄂A88888");
        Ticket ticket = parkingLot.park(car);
        assertThat(ticket).isNotEqualTo(null);
        assertThat(ticket.getCarNumber()).isEqualTo(car.getCarNumber());
    }

    @Test
    void should_failed_to_park_when_park_car_given_one_car_and_full_parking_lot() {
        Car car = new Car("鄂A88888");
        parkingLot.park(car);

        Car anotherCar = new Car("鄂A88888");
        assertThatThrownBy(() -> parkingLot.park(anotherCar)).isInstanceOf(ParkingLotFullException.class);
    }

    @Test
    void should_succeed_to_pick_up_when_pick_up_given_one_ticket_and_car_exist_in_parking_lot() {
        Car car = new Car("鄂A88888");
        Ticket ticket = parkingLot.park(car);

        Car pickUpCar = parkingLot.pickUp(ticket);
        assertThat(pickUpCar.getCarNumber()).isEqualTo(car.getCarNumber());
    }

    @Test
    void should_failed_to_pick_up_when_pick_up_given_one_ticket_and_car_not_exist_in_parking_lot() {
        Ticket ticket = new Ticket();
        ticket.setCarNumber("鄂A66666");

        assertThatThrownBy(() -> parkingLot.pickUp(ticket)).isInstanceOf(InvalidTicketException.class);
    }

    @Test
    void should_failed_to_pick_up_when_pick_up_given_one_ticket_and_car_has_been_picked_up() {
        Car car = new Car("鄂A88888");
        Ticket ticket = parkingLot.park(car);
        parkingLot.pickUp(ticket);

        assertThatThrownBy(() -> parkingLot.pickUp(ticket)).isInstanceOf(InvalidTicketException.class);
    }


}
