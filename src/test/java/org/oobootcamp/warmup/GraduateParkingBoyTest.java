package org.oobootcamp.warmup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oobootcamp.warmup.domain.Car;
import org.oobootcamp.warmup.domain.Ticket;
import org.oobootcamp.warmup.exception.AllParkingLotsFullException;
import org.oobootcamp.warmup.exception.CarNotFoundInAllParkingLotsException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author Super Idol Pair B
 * @date 2022/5/27
 */
public class GraduateParkingBoyTest {
    GraduateParkingBoy graduateParkingBoy;
    ParkingLot parkingLot1;
    ParkingLot parkingLot2;

    @BeforeEach
    void setUp() {
        graduateParkingBoy = new GraduateParkingBoy();
        parkingLot1 = new ParkingLot(1);
        parkingLot2 = new ParkingLot(1);
        graduateParkingBoy.addParkingLot(parkingLot1);
        graduateParkingBoy.addParkingLot(parkingLot2);
    }

    @Test
    void should_park_to_first_not_full_parking_lot_when_park_car_given_one_car_and_all_ordered_parking_lots_not_full() {
        Car car1 = new Car();
        car1.setCarNumber("鄂A88888");

        Ticket car1Ticket = graduateParkingBoy.park(car1);
        assertThat(car1Ticket.getCarNumber()).isEqualTo(car1.getCarNumber());
        assertThat(parkingLot1.isCarIn(car1.getCarNumber())).isEqualTo(true);

        Car car2 = new Car();
        car2.setCarNumber("鄂A66666");

        Ticket car2Ticket = graduateParkingBoy.park(car2);
        assertThat(car2Ticket.getCarNumber()).isEqualTo(car2.getCarNumber());
        assertThat(parkingLot1.isCarIn(car2.getCarNumber())).isEqualTo(false);
        assertThat(parkingLot2.isCarIn(car2.getCarNumber())).isEqualTo(true);
    }

    @Test
    void should_park_to_first_not_full_parking_lot_when_park_car_given_one_car_and_first_parking_lot_full_and_rest_parking_lots_not_full() {
        Car car1 = new Car();
        car1.setCarNumber("鄂A88888");
        graduateParkingBoy.park(car1);

        Car car2 = new Car();
        car2.setCarNumber("鄂A66666");

        Ticket car2Ticket = graduateParkingBoy.park(car2);
        assertThat(car2Ticket.getCarNumber()).isEqualTo(car2.getCarNumber());
        assertThat(parkingLot1.isCarIn(car2.getCarNumber())).isEqualTo(false);
        assertThat(parkingLot2.isCarIn(car2.getCarNumber())).isEqualTo(true);
    }

    @Test
    void should_failed_to_park_when_park_car_given_one_car_and_all_full_ordered_parking_lots() {
        Car car1 = new Car();
        car1.setCarNumber("鄂A88888");
        graduateParkingBoy.park(car1);

        Car car2 = new Car();
        car2.setCarNumber("鄂A66666");
        graduateParkingBoy.park(car2);

        Car car3 = new Car();
        car3.setCarNumber("鄂A55555");
        assertThatThrownBy(() -> graduateParkingBoy.park(car3)).isInstanceOf(AllParkingLotsFullException.class);
    }

    @Test
    void should_succeed_to_pick_up_when_pick_up_given_one_ticket_and_car_exist_in_ordered_parking_lots() {
        Car expectedCar = new Car();
        expectedCar.setCarNumber("鄂A88888");
        Ticket ticket = graduateParkingBoy.park(expectedCar);

        Car pickedUpCar = graduateParkingBoy.pickUp(ticket);
        assertThat(pickedUpCar.getCarNumber()).isEqualTo(expectedCar.getCarNumber());
    }

    @Test
    void should_failed_to_pick_up_when_pick_up_given_one_ticket_and_car_never_park_in_ordered_parking_lots() {
        Ticket ticket = new Ticket();
        ticket.setCarNumber("鄂A66666");

        assertThatThrownBy(() -> graduateParkingBoy.pickUp(ticket)).isInstanceOf(CarNotFoundInAllParkingLotsException.class);
    }

    @Test
    void should_failed_to_pick_up_when_pick_up_given_one_ticket_and_car_already_picked_up_from_ordered_parking_lots() {
        Car car = new Car();
        car.setCarNumber("鄂A88888");
        Ticket ticket = graduateParkingBoy.park(car);

        graduateParkingBoy.pickUp(ticket);

        assertThatThrownBy(() -> graduateParkingBoy.pickUp(ticket)).isInstanceOf(CarNotFoundInAllParkingLotsException.class);
    }
}
