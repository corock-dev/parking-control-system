package com.nhnacademy.pms.tdd;

public class AdditionalParkingFee implements ParkingFee {
    private final Money fee;

    public AdditionalParkingFee(Money fee) {
        this.fee = fee;
    }

    @Override
    public long getAmount() {
        return fee.getAmount();
    }
}
