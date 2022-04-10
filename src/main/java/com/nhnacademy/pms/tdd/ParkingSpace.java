package com.nhnacademy.pms.tdd;

import java.time.LocalDateTime;

public class ParkingSpace {
    private final ParkingSpaceCode code;
    private final Car car;
    private final LocalDateTime parkingTime;

    public ParkingSpace(ParkingSpaceCode code, Car car, LocalDateTime parkingTime) {
        this.code = code;
        this.car = car;
        this.parkingTime = parkingTime;
    }

    public Car getCar() {
        return car;
    }

    public LocalDateTime getParkingTime() {
        return parkingTime;
    }

    // interface Displayable {
    //     void display();
    // }

    enum ParkingSpaceCode /* implements Displayable */ {
        A1
    }
}
