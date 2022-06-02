package org.oobootcamp.warmup;

import org.junit.jupiter.api.Test;
import org.oobootcamp.warmup.domain.Car;
import org.oobootcamp.warmup.domain.Ticket;
import org.oobootcamp.warmup.exception.AllParkingLotsFullException;
import org.oobootcamp.warmup.exception.InvalidTicketException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParkingManagerTest {
    @Test
    void should_succeed_to_park_by_first_parking_boy_when_park_car_given_one_car_and_one_parking_manager_manage_two_parking_boys() {
        Car expectedCar = new Car("鄂A88888");

        ParkingLot parkingLotManagedByParkingBoy1 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingBoy2 = new ParkingLot(1);
        ParkingBoy parkingBoy1 = new GraduateParkingBoy(List.of((parkingLotManagedByParkingBoy1)));
        ParkingBoy parkingBoy2 = new SmartParkingBoy(List.of(parkingLotManagedByParkingBoy2));

        ParkingManager parkingManager = new ParkingManager(List.of(parkingBoy1, parkingBoy2));

        Ticket ticket = parkingManager.park(expectedCar);

        assertThat(parkingLotManagedByParkingBoy1.pickUp(ticket)).isEqualTo(expectedCar);
    }

    @Test
    void should_succeed_to_park_by_second_parking_boy_when_park_car_given_one_car_and_one_parking_manager_manage_two_parking_boys() {
        Car car1 = new Car("鄂A88888");
        Car car2 = new Car("鄂A66666");

        ParkingLot parkingLotManagedByParkingBoy1 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingBoy2 = new ParkingLot(1);
        ParkingBoy parkingBoy1 = new GraduateParkingBoy(List.of((parkingLotManagedByParkingBoy1)));
        ParkingBoy parkingBoy2 = new SmartParkingBoy(List.of(parkingLotManagedByParkingBoy2));

        ParkingManager parkingManager = new ParkingManager(List.of(parkingBoy1, parkingBoy2));

        parkingLotManagedByParkingBoy1.park(car1);
        Ticket car2Ticket = parkingManager.park(car2);

        assertThat(parkingLotManagedByParkingBoy2.pickUp(car2Ticket)).isEqualTo(car2);
    }

    @Test
    void should_succeed_to_park_by_first_parking_boy_when_park_car_given_one_car_and_one_parking_manager_manage_two_parking_boys_and_ordered_parking_lots() {
        Car expectedCar = new Car("鄂A88888");

        ParkingLot parkingLotManagedByParkingBoy1 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingBoy2 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingManager = new ParkingLot(1);
        ParkingBoy parkingBoy1 = new GraduateParkingBoy(List.of((parkingLotManagedByParkingBoy1)));
        ParkingBoy parkingBoy2 = new SmartParkingBoy(List.of(parkingLotManagedByParkingBoy2));

        ParkingManager parkingManager = new ParkingManager(List.of(parkingBoy1, parkingBoy2, parkingLotManagedByParkingManager));

        Ticket car1Ticket = parkingManager.park(expectedCar);

        assertThat(parkingLotManagedByParkingBoy1.pickUp(car1Ticket)).isEqualTo(expectedCar);
    }

    @Test
    void should_succeed_to_park_by_parking_manager_when_park_car_given_one_car_and_one_parking_manager_manage_two_parking_boys_and_ordered_parking_lots() {
        Car car1 = new Car("鄂A88888");
        Car car2 = new Car("鄂A66666");
        Car car3 = new Car("鄂A55555");

        ParkingLot parkingLotManagedByParkingBoy1 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingBoy2 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingManager = new ParkingLot(1);
        ParkingBoy parkingBoy1 = new GraduateParkingBoy(List.of((parkingLotManagedByParkingBoy1)));
        ParkingBoy parkingBoy2 = new SmartParkingBoy(List.of(parkingLotManagedByParkingBoy2));

        ParkingManager parkingManager = new ParkingManager(List.of(parkingBoy1, parkingBoy2, parkingLotManagedByParkingManager));

        parkingLotManagedByParkingBoy1.park(car1);
        parkingLotManagedByParkingBoy2.park(car2);

        Ticket car3Ticket = parkingManager.park(car3);

        assertThat(parkingLotManagedByParkingManager.pickUp(car3Ticket)).isEqualTo(car3);
    }

    @Test
    void should_failed_to_park_when_park_car_given_one_car_and_one_parking_manager_manage_two_parking_boys_and_all_parking_lots_full() {
        Car car1 = new Car("鄂A88888");
        Car car2 = new Car("鄂A66666");
        Car car3 = new Car("鄂A55555");

        ParkingLot parkingLotManagedByParkingBoy1 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingBoy2 = new ParkingLot(1);
        ParkingBoy parkingBoy1 = new GraduateParkingBoy(List.of((parkingLotManagedByParkingBoy1)));
        ParkingBoy parkingBoy2 = new SmartParkingBoy(List.of(parkingLotManagedByParkingBoy2));

        ParkingManager parkingManager = new ParkingManager(List.of(parkingBoy1, parkingBoy2));

        parkingLotManagedByParkingBoy1.park(car1);
        parkingLotManagedByParkingBoy2.park(car2);

        assertThatThrownBy(() -> parkingManager.park(car3)).isInstanceOf(AllParkingLotsFullException.class);
    }

    @Test
    void should_failed_to_park_when_park_car_given_one_car_and_one_parking_manager_manage_two_parking_boys_and_ordered_parking_lots_and_all_parking_lots_full() {
        Car car1 = new Car("鄂A88888");
        Car car2 = new Car("鄂A66666");
        Car car3 = new Car("鄂A55555");
        Car car4 = new Car("鄂A33333");

        ParkingLot parkingLotManagedByParkingBoy1 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingBoy2 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingManager = new ParkingLot(1);

        ParkingBoy parkingBoy1 = new GraduateParkingBoy(List.of((parkingLotManagedByParkingBoy1)));
        ParkingBoy parkingBoy2 = new SmartParkingBoy(List.of(parkingLotManagedByParkingBoy2));

        ParkingManager parkingManager = new ParkingManager(List.of(parkingBoy1, parkingBoy2, parkingLotManagedByParkingManager));

        parkingLotManagedByParkingBoy1.park(car1);
        parkingLotManagedByParkingBoy2.park(car2);
        parkingLotManagedByParkingManager.park(car3);

        assertThatThrownBy(() -> parkingManager.park(car4)).isInstanceOf(AllParkingLotsFullException.class);
    }

    @Test
    void should_succeed_to_pick_up_when_pick_up_given_one_valid_ticket_and_one_parking_manager_manage_two_parking_boys() {
        Car expectedCar = new Car("鄂A88888");

        ParkingLot parkingLotManagedByParkingBoy1 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingBoy2 = new ParkingLot(1);

        ParkingBoy parkingBoy1 = new GraduateParkingBoy(List.of((parkingLotManagedByParkingBoy1)));
        ParkingBoy parkingBoy2 = new SmartParkingBoy(List.of(parkingLotManagedByParkingBoy2));

        ParkingManager parkingManager = new ParkingManager(List.of(parkingBoy1, parkingBoy2));

        Ticket ticket = parkingLotManagedByParkingBoy1.park(expectedCar);

        assertThat(parkingManager.pickUp(ticket)).isEqualTo(expectedCar);
    }

    @Test
    void should_succeed_to_pick_up_when_pick_up_given_one_valid_ticket_and_one_parking_manager_manage_two_parking_boys_and_ordered_parking_lots_and_car_in_parking_lots_managed_by_parking_boys() {
        Car expectedCar = new Car("鄂A88888");

        ParkingLot parkingLotManagedByParkingBoy1 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingBoy2 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingManager = new ParkingLot(1);

        ParkingBoy parkingBoy1 = new GraduateParkingBoy(List.of((parkingLotManagedByParkingBoy1)));
        ParkingBoy parkingBoy2 = new SmartParkingBoy(List.of(parkingLotManagedByParkingBoy2));

        ParkingManager parkingManager = new ParkingManager(List.of(parkingBoy1, parkingBoy2, parkingLotManagedByParkingManager));

        Ticket ticket = parkingLotManagedByParkingBoy1.park(expectedCar);

        assertThat(parkingManager.pickUp(ticket)).isEqualTo(expectedCar);
    }

    @Test
    void should_succeed_to_pick_up_when_pick_up_given_one_valid_ticket_and_one_parking_manager_manage_two_parking_boys_and_ordered_parking_lots_and_car_in_parking_lots_managed_by_parking_manager() {
        Car expectedCar = new Car("鄂A88888");

        ParkingLot parkingLotManagedByParkingBoy1 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingBoy2 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingManager = new ParkingLot(1);

        ParkingBoy parkingBoy1 = new GraduateParkingBoy(List.of((parkingLotManagedByParkingBoy1)));
        ParkingBoy parkingBoy2 = new SmartParkingBoy(List.of(parkingLotManagedByParkingBoy2));

        ParkingManager parkingManager = new ParkingManager(List.of(parkingBoy1, parkingBoy2, parkingLotManagedByParkingManager));

        Ticket ticket = parkingLotManagedByParkingManager.park(expectedCar);

        assertThat(parkingManager.pickUp(ticket)).isEqualTo(expectedCar);
    }

    @Test
    void should_failed_to_pick_up_when_pick_up_given_one_invalid_ticket_and_one_parking_manager_manage_two_parking_boys() {
        ParkingLot parkingLotManagedByParkingBoy1 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingBoy2 = new ParkingLot(1);

        ParkingBoy parkingBoy1 = new GraduateParkingBoy(List.of((parkingLotManagedByParkingBoy1)));
        ParkingBoy parkingBoy2 = new SmartParkingBoy(List.of(parkingLotManagedByParkingBoy2));

        ParkingManager parkingManager = new ParkingManager(List.of(parkingBoy1, parkingBoy2));

        Ticket ticket = new Ticket();
        ticket.setCarNumber("鄂A12345");

        assertThatThrownBy(() -> parkingManager.pickUp(ticket)).isInstanceOf(InvalidTicketException.class);

    }

    @Test
    void should_failed_to_pick_up_when_pick_up_given_one_used_ticket_and_one_parking_manager_manage_two_parking_boys() {
        Car car = new Car("鄂A88888");

        ParkingLot parkingLotManagedByParkingBoy1 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingBoy2 = new ParkingLot(1);

        ParkingBoy parkingBoy1 = new GraduateParkingBoy(List.of((parkingLotManagedByParkingBoy1)));
        ParkingBoy parkingBoy2 = new SmartParkingBoy(List.of(parkingLotManagedByParkingBoy2));

        ParkingManager parkingManager = new ParkingManager(List.of(parkingBoy1, parkingBoy2));

        Ticket ticket = parkingManager.park(car);
        parkingManager.pickUp(ticket);

        assertThatThrownBy(() -> parkingManager.pickUp(ticket)).isInstanceOf(InvalidTicketException.class);

    }

    @Test
    void should_failed_to_pick_up_when_pick_up_given_one_invalid_ticket_and_one_parking_manager_manage_two_parking_boys_and_ordered_parking_lots() {
        ParkingLot parkingLotManagedByParkingBoy1 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingBoy2 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingManager = new ParkingLot(1);

        ParkingBoy parkingBoy1 = new GraduateParkingBoy(List.of((parkingLotManagedByParkingBoy1)));
        ParkingBoy parkingBoy2 = new SmartParkingBoy(List.of(parkingLotManagedByParkingBoy2));

        ParkingManager parkingManager = new ParkingManager(List.of(parkingBoy1, parkingBoy2, parkingLotManagedByParkingManager));

        Ticket ticket = new Ticket();
        ticket.setCarNumber("鄂A12345");

        assertThatThrownBy(() -> parkingManager.pickUp(ticket)).isInstanceOf(InvalidTicketException.class);

    }

    @Test
    void should_failed_to_pick_up_when_pick_up_given_one_used_ticket_and_one_parking_manager_manage_two_parking_boys_and_ordered_parking_lots() {
        Car car = new Car("鄂A88888");

        ParkingLot parkingLotManagedByParkingBoy1 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingBoy2 = new ParkingLot(1);
        ParkingLot parkingLotManagedByParkingManager = new ParkingLot(1);

        ParkingBoy parkingBoy1 = new GraduateParkingBoy(List.of((parkingLotManagedByParkingBoy1)));
        ParkingBoy parkingBoy2 = new SmartParkingBoy(List.of(parkingLotManagedByParkingBoy2));

        ParkingManager parkingManager = new ParkingManager(List.of(parkingBoy1, parkingBoy2, parkingLotManagedByParkingManager));

        Ticket ticket = parkingManager.park(car);
        parkingManager.pickUp(ticket);

        assertThatThrownBy(() -> parkingManager.pickUp(ticket)).isInstanceOf(InvalidTicketException.class);

    }
}
