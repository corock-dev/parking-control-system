package com.nhnacademy.pms.tdd;

public class OneDayParkingFee implements ParkingFee {
    private final Money fee;

    public OneDayParkingFee(Money fee) {
        this.fee = fee;
    }

    @Override
    public long getAmount() {
        return fee.getAmount();
    }
}
