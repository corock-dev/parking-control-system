package com.nhnacademy.pms.tdd;

import static com.nhnacademy.pms.tdd.Money.Currency.WON;

import com.nhnacademy.pms.tdd.exception.NotEnoughMoneyException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingManagementService {
    private final ParkingLotRepository repository;

    public ParkingManagementService(ParkingLotRepository repository) {
        this.repository = repository;
    }

    public void parkAt(ParkingSpace parkingSpace) {
        LocalDateTime.of(2022, 4, 9, 16, 0, 0);
    }

    public void pay(Car car) {
        ParkingSpace space = repository.findParkingSpaceByLicenseNumber(car.getLicenseNumber());
        User user = repository.findUserByParkingSpaceCar(space);
        Money money = user.getMoney();

        if (money.getAmount() < 1_000L) {
            throw new NotEnoughMoneyException(user.getUserId()
                + "(" + user.getCar().getLicenseNumber() + ") have not enough money");
        }

        repository.getExits().pay(car);
    }

    public List<ParkingFee> exit(Car car, LocalDateTime parkingDateTime) {
        ParkingSpace space = repository.findParkingSpaceByLicenseNumber(car.getLicenseNumber());
        User user = repository.findUserByParkingSpaceCar(space);
        Money money = user.getMoney();

        LocalDateTime parkingTime = space.getParkingTime();
        Duration duration = Duration.between(parkingTime, parkingDateTime);
        int minutesDiff = duration.toMinutesPart();
        long daysDiff = duration.toDaysPart();
        List<ParkingFee> parkingFees = new ArrayList<>();

        if (daysDiff == 0 && minutesDiff <= 30) {
            parkingFees.add(new HalfPastParkingFee(new Money(1_000L, WON)));
        } else if (daysDiff == 1) {
            parkingFees.add(new OneDayParkingFee(new Money(10_000L, WON)));
        } else {
            parkingFees.add(new AdditionalParkingFee(new Money(500L, WON)));
        }

        return parkingFees;
    }
}
