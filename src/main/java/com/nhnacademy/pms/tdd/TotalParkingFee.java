package com.nhnacademy.pms.tdd;

public class TotalParkingFee extends ParkingFee {
    public TotalParkingFee(Money money) {
        super();
    }

    @Override
    public Money discount(Money initParkingFee) {
        return null;
    }

    @Override
    long getAmount() {
        return 0;
    }
}
