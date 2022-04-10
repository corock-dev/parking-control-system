package com.nhnacademy.pms.tdd;

import static com.nhnacademy.pms.tdd.Car.CarType.LARGE;

import com.nhnacademy.pms.tdd.exception.CannotBeParkedCarTypeException;

public class Entrance {
    public String scanLicensePlateNumber(Car car) {
        if (car.getLicenseNumber() == null) {
            throw new IllegalArgumentException("null");
        }
        return car.getLicenseNumber();
    }

    public void scanCarType(Car car) {
        if (car.getType() == LARGE) {
            throw new CannotBeParkedCarTypeException("The large car cannot be parked");
        }
    }
}
