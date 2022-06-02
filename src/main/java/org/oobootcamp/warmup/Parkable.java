package org.oobootcamp.warmup;

import org.oobootcamp.warmup.domain.Car;
import org.oobootcamp.warmup.domain.Ticket;

/**
 * @author Super Idol Pair B
 * @date 2022/6/2
 */
public interface Parkable {

    Ticket park(Car car);

    Car pickUp(Ticket ticket);

    boolean isFull();

    boolean isCarIn(Ticket ticket);
}
