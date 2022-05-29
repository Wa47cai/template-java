package org.oobootcamp.warmup.domain;

import lombok.Getter;

import java.util.Objects;

/**
 * @author Super Idol Pair B
 * @date 2022/5/25
 */
@Getter
public class Car {

    private final String carNumber;

    public Car(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Car car)) {
            return false;
        }
        return Objects.equals(getCarNumber(), car.getCarNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCarNumber());
    }
}
