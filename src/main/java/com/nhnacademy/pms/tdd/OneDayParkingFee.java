package com.nhnacademy.pms.tdd;

public class OneDayParkingFee extends ParkingFee {
    private final Money fee;

    public OneDayParkingFee(Money fee) {
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
