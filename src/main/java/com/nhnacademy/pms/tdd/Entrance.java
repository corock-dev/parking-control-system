package com.nhnacademy.pms.tdd;

public class Entrance {
    String scanLicensePlateNumber(Car car) {
        if (car.getLicenseNumber() == null) {
            throw new IllegalArgumentException("null");
        }
        return car.getLicenseNumber();
    }
}
