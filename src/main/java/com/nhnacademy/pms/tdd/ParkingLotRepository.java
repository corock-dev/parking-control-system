package com.nhnacademy.pms.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotRepository {
    private final Entrance entrance;
    private final List<ParkingSpace> spaces;

    public ParkingLotRepository(Entrance enterance) {
        this.entrance = enterance;
        this.spaces = new ArrayList<>();
    }

    public void enter(Car car) {
        this.entrance.scanLicensePlateNumber(car);
    }

    public ParkingSpace findCarByLicenseNumber(String licenseNumber) {
        return null;
    }
}
