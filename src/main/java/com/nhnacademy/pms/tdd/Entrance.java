package com.nhnacademy.pms.tdd;

import com.nhnacademy.pms.tdd.exception.CannotBeParkedCarTypeException;

public class Entrance {
    String scanLicensePlateNumber(Car car) {
        if (car.getLicenseNumber() == null) {
            throw new IllegalArgumentException("null");
        }
        return car.getLicenseNumber();
    }

    public void scanCarType(Car car) {
        if (car instanceof LargeCar) {
            throw new CannotBeParkedCarTypeException("The large car cannot be parked");
        }
    }
}
