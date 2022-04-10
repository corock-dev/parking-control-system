package com.nhnacademy.pms.tdd;

public class Car {
    private final String licenseNumber;
    private final CarType type;

    public Car(String licenseNumber, CarType type) {
        this.licenseNumber = licenseNumber;
        this.type = type;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public CarType getType() {
        return type;
    }

    enum CarType {
        COMPACT,
        LARGE
    }
}
