package com.nhnacademy.pms.tdd;

public class ParkingManagementService {
    private final ParkingLotRepository repository;

    public ParkingManagementService(ParkingLotRepository repository) {
        this.repository = repository;
    }

    public void parkAt(ParkingSpace parkingSpace) {
    }
}
