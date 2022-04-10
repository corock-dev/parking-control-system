package com.nhnacademy.pms.tdd;

public class User {
    private final String userId;
    private final Money money;
    private final Car car;

    public User(String userId, Money money, Car car) {
        this.userId = userId;
        this.money = money;
        this.car = car;
    }

    public String getUserId() {
        return userId;
    }

    public Money getMoney() {
        return money;
    }

    public Car getCar() {
        return car;
    }
}
