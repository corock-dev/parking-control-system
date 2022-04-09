package com.nhnacademy.pms.tdd;

public class ParkingSpace {
    private final ParkingSpaceCode code;
    private final Car car;

    public ParkingSpace(ParkingSpaceCode code, Car car) {
        this.code = code;
        this.car = car;
    }

    // interface Displayable {
    //     void display();
    // }

    enum ParkingSpaceCode /* implements Displayable */ {
        A1
    }
}
