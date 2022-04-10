package com.nhnacademy.pms.tdd;

public class RevisedInitHalfPastParkingFee extends ParkingFee {
    @Override
    public long getAmount() {
        return 0;
    }

    @Override
    public Money discount(Money initParkingFee) {
        return null;
    }
}
