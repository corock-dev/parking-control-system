package com.nhnacademy.pms.tdd;

import static com.nhnacademy.pms.tdd.User.Membership.ANONYMOUS;

public class User {
    private final String userId;
    private final Money money;
    private final Car car;
    private final Membership membership;

    public User(String userId, Money money, Car car) {
        this(userId, money, car, ANONYMOUS);
    }

    public User(String userId, Money money, Car car, Membership membership) {
        this.userId = userId;
        this.money = money;
        this.car = car;
        this.membership = membership;
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

    enum Membership {
        ANONYMOUS,
        PAYCO
    }
}
