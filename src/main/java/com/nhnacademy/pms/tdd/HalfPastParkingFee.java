package com.nhnacademy.pms.tdd;

public class HalfPastParkingFee extends ParkingFee {
    private final Money fee;

    public HalfPastParkingFee(Money fee) {
        this.fee = fee;
    }

    @Override
    public long getAmount() {
        return fee.getAmount();
    }

    @Override
    public Money discount(Money initParkingFee) {
        return null;
    }
}
