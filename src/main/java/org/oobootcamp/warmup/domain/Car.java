package org.oobootcamp.warmup.domain;

import lombok.Getter;
import lombok.Setter;

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
}
