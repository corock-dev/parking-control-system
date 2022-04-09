package com.nhnacademy.pms.tdd;

public class Entrance {
    String scan(Car car) {
        return this.scanLicensePlate(car);
    }

    private String scanLicensePlate(Car car) {
        return car.getNumber();
    }
}
