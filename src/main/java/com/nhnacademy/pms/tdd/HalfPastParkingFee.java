package com.nhnacademy.pms.tdd;

public class HalfPastParkingFee implements ParkingFee {
    private final Money fee;

    public HalfPastParkingFee(Money fee) {
        this.fee = fee;
    }

    @Override
    public long getAmount() {
        return fee.getAmount();
    }
}
