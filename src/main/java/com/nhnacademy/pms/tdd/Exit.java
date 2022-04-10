package com.nhnacademy.pms.tdd;

import static com.nhnacademy.pms.tdd.Car.CarType.COMPACT;
import static com.nhnacademy.pms.tdd.Money.Currency.WON;

import java.time.LocalDateTime;

public class Exit {
    public ParkingFee pay(Car car) {
        return null;
    }

    public ParkingFee pay(Car car, LocalDateTime endParkingTime) {
        if (car.getType() == COMPACT) {
            return new HalfPastParkingFee(new Money(500L, WON));
        }
        return null;
    }
}
