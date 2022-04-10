package com.nhnacademy.pms.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotRepository {
    private final List<ParkingSpace> spaces;
    private final Entrance entrance;
    private final Exit exit;

    public ParkingLotRepository(Entrance enterance, Exit exit) {
        this.entrance = enterance;
        this.exit = exit;
        this.spaces = new ArrayList<>();
    }

    public ParkingSpace findParkingSpaceByLicenseNumber(String licenseNumber) {
        return null;
    }

    public void enter(Car car) {
        this.entrance.scanLicensePlateNumber(car);
    }

    public Bill exit() {
        return null;
    }

    public User findUserByParkingSpaceCar(ParkingSpace space) {
        return null;
    }

    public Exit getExit() {
        return exit;
    }
}
