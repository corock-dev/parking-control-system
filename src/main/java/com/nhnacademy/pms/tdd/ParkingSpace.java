package com.nhnacademy.pms.tdd;

import java.time.LocalDateTime;

public class ParkingSpace {
    private final ParkingSpaceCode code;
    private final Car car;
    private final LocalDateTime parkingTime;
    private final boolean canBeParked;

    public ParkingSpace(ParkingSpaceCode code, Car car, LocalDateTime parkingTime) {
        this(code, car, parkingTime, true);
    }

    public ParkingSpace(ParkingSpaceCode code, Car car, LocalDateTime parkingTime,
                        boolean canBeParked) {
        this.code = code;
        this.car = car;
        this.parkingTime = parkingTime;
        this.canBeParked = canBeParked;
    }

    public Car getCar() {
        return car;
    }

    public LocalDateTime getParkingTime() {
        return parkingTime;
    }

    enum ParkingSpaceCode /* implements Displayable */ {
        A1
    }
}
