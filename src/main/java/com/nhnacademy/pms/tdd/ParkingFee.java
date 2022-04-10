package com.nhnacademy.pms.tdd;

import static com.nhnacademy.pms.tdd.Money.Currency.WON;

public abstract class ParkingFee implements Discountable {
    abstract long getAmount();

    public ParkingFee add(AdditionalParkingFee additionalParkingFee) {
        return new TotalParkingFee(
            new Money(this.getAmount() + additionalParkingFee.getAmount(), WON));
    }
}
