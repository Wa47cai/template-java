package org.oobootcamp.warmup;

import org.junit.jupiter.api.Test;
import org.oobootcamp.warmup.domain.Car;
import org.oobootcamp.warmup.domain.Ticket;
import org.oobootcamp.warmup.exception.AllParkingLotsFullException;
import org.oobootcamp.warmup.exception.InvalidTicketException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SmartParkingBoyTest {
    SmartParkingBoy smartParkingBoy;
    ParkingLot parkingLot1;
    ParkingLot parkingLot2;


    @Test
    void should_park_to_parking_lot_2_when_park_car_given_one_car_and_parking_lot_1_with_1_free_space_and_parking_lot_2_with_2_free_space() {
        parkingLot1 = new ParkingLot(1);
        parkingLot2 = new ParkingLot(2);
        smartParkingBoy = new SmartParkingBoy(List.of(parkingLot1, parkingLot2));

        Car expectedCar = new Car("鄂A88888");
        Ticket ticket = smartParkingBoy.park(expectedCar);

        assertThat(ticket.getCarNumber()).isEqualTo(expectedCar.getCarNumber());
        assertThat(parkingLot2.pickUp(ticket)).isEqualTo(expectedCar);
    }

    @Test
    void should_park_to_parking_lot_1_when_park_car_given_one_car_and_parking_lot_1_with_1_free_space_and_parking_lot_2_with_1_free_space() {
        parkingLot1 = new ParkingLot(1);
        parkingLot2 = new ParkingLot(1);
        smartParkingBoy = new SmartParkingBoy(List.of(parkingLot1, parkingLot2));

        Car expectedCar = new Car("鄂A88888");
        Ticket ticket = smartParkingBoy.park(expectedCar);

        assertThat(ticket.getCarNumber()).isEqualTo(expectedCar.getCarNumber());
        assertThat(parkingLot1.pickUp(ticket)).isEqualTo(expectedCar);
    }

    @Test
    void should_park_to_parking_lot_2_when_park_car_given_one_car_and_parking_lot_1_with_1_free_space_and_parking_lot_2_3_with_2_free_space() {
        parkingLot1 = new ParkingLot(1);
        parkingLot2 = new ParkingLot(2);
        ParkingLot parkingLot3 = new ParkingLot(2);
        smartParkingBoy = new SmartParkingBoy(List.of(parkingLot1, parkingLot2, parkingLot3));

        Car expectedCar = new Car("鄂A88888");
        Ticket ticket = smartParkingBoy.park(expectedCar);

        assertThat(ticket.getCarNumber()).isEqualTo(expectedCar.getCarNumber());
        assertThat(parkingLot2.pickUp(ticket)).isEqualTo(expectedCar);
    }

    @Test
    void should_park_to_parking_lot_3_when_park_car_given_one_car_and_parking_lot_1_2_with_1_free_space_and_parking_lot_3_with_2_free_space() {
        parkingLot1 = new ParkingLot(1);
        parkingLot2 = new ParkingLot(1);
        ParkingLot parkingLot3 = new ParkingLot(2);
        smartParkingBoy = new SmartParkingBoy(List.of(parkingLot1, parkingLot2, parkingLot3));

        Car expectedCar = new Car("鄂A88888");
        Ticket ticket = smartParkingBoy.park(expectedCar);

        assertThat(ticket.getCarNumber()).isEqualTo(expectedCar.getCarNumber());
        assertThat(parkingLot3.pickUp(ticket)).isEqualTo(expectedCar);
    }

    @Test
    void should_failed_to_park_when_park_car_given_one_car_and_all_full_ordered_parking_lots() {
        parkingLot1 = new ParkingLot(1);
        parkingLot2 = new ParkingLot(1);
        smartParkingBoy = new SmartParkingBoy(List.of(parkingLot1, parkingLot2));

        Car car1 = new Car("鄂A88888");
        parkingLot1.park(car1);
        Car car2 = new Car("鄂A66666");
        parkingLot2.park(car2);

        Car car3 = new Car("鄂A55555");

        assertThatThrownBy(() -> smartParkingBoy.park(car3)).isInstanceOf(AllParkingLotsFullException.class);
    }

    @Test
    void should_succeed_to_pick_up_when_pick_up_given_one_ticket_and_car_exist_in_ordered_parking_lots() {
        parkingLot1 = new ParkingLot(1);
        parkingLot2 = new ParkingLot(1);
        smartParkingBoy = new SmartParkingBoy(List.of(parkingLot1, parkingLot2));

        Car expectedCar = new Car("鄂A88888");
        Ticket ticket = smartParkingBoy.park(expectedCar);

        Car pickedUpCar = smartParkingBoy.pickUp(ticket);
        assertThat(pickedUpCar).isEqualTo(expectedCar);
    }

    @Test
    void should_failed_to_pick_up_when_pick_up_given_one_ticket_and_car_never_park_in_ordered_parking_lots() {
        parkingLot1 = new ParkingLot(1);
        parkingLot2 = new ParkingLot(1);
        smartParkingBoy = new SmartParkingBoy(List.of(parkingLot1, parkingLot2));

        Ticket ticket = new Ticket();
        ticket.setCarNumber("鄂A66666");

        assertThatThrownBy(() -> smartParkingBoy.pickUp(ticket)).isInstanceOf(InvalidTicketException.class);
    }

    @Test
    void should_failed_to_pick_up_when_pick_up_given_one_ticket_and_car_already_picked_up_from_ordered_parking_lots() {
        parkingLot1 = new ParkingLot(1);
        parkingLot2 = new ParkingLot(1);
        smartParkingBoy = new SmartParkingBoy(List.of(parkingLot1, parkingLot2));

        Car car = new Car("鄂A88888");
        Ticket ticket = smartParkingBoy.park(car);

        smartParkingBoy.pickUp(ticket);

        assertThatThrownBy(() -> smartParkingBoy.pickUp(ticket)).isInstanceOf(InvalidTicketException.class);
    }
}
