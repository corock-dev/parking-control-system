package com.nhnacademy.pms.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotRepository {
    private final List<ParkingSpace> spaces;
    private final List<Entrance> entrances;
    private final List<Exit> exits;

    public ParkingLotRepository(Entrance enterance, Exit exit) {
        this.entrances = new ArrayList<>();
        this.exits = new ArrayList<>();
        this.spaces = new ArrayList<>();

        this.entrances.add(enterance);
        this.exits.add(exit);
    }

    public ParkingLotRepository(List<Entrance> entrances, List<Exit> exits,
                                List<ParkingSpace> spaces) {
        this.spaces = spaces;
        this.entrances = entrances;
        this.exits = exits;
    }

    public ParkingSpace findParkingSpaceByLicenseNumber(String licenseNumber) {
        return null;
    }

    public void enter(Car car) {
        String licenseNumber = this.entrances.get(0).scanLicensePlateNumber(car);
    }

    public ParkingFee exit(Car car) {
        return exits.get(0).pay(car);
    }

    public User findUserByParkingSpaceCar(ParkingSpace space) {
        return null;
    }

    public Exit getExits() {
        return exits.get(0);
    }
}
