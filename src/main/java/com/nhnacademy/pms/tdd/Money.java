package com.nhnacademy.pms.tdd;

public class Money {
    private final long amount;
    private final Currency currency;

    public Money(long amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public long getAmount() {
        return amount;
    }

    public enum Currency {
        WON
    }
}
