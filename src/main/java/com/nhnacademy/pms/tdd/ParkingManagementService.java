package com.nhnacademy.pms.tdd;

import com.nhnacademy.pms.tdd.exception.NotEnoughMoneyException;

public class ParkingManagementService {
    private final ParkingLotRepository repository;

    public ParkingManagementService(ParkingLotRepository repository) {
        this.repository = repository;
    }

    public void parkAt(ParkingSpace parkingSpace) {
    }

    public void pay(Car car) {
        ParkingSpace space = repository.findParkingSpaceByLicenseNumber(car.getLicenseNumber());
        User user = repository.findUserByParkingSpaceCar(space);
        Money money = user.getMoney();

        if (money.getAmount() < 1_000L) {
            throw new NotEnoughMoneyException(user.getUserId()
                + "(" + user.getCar().getLicenseNumber() + ") have not enough money");
        }

        repository.getExit().pay(car);
    }
}
