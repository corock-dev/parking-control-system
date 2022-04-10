package com.nhnacademy.pms.tdd;

public class AdditionalParkingFee extends ParkingFee {
    private final Money fee;

    public AdditionalParkingFee(Money fee) {
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
