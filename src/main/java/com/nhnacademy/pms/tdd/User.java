package com.nhnacademy.pms.tdd;

import static com.nhnacademy.pms.tdd.User.Membership.ANONYMOUS;

import java.util.List;

public class User {
    private final String userId;
    private final Money money;
    private final Car car;
    private final Membership membership;
    private final List<ParkingTicket> tickets;

    public User(String userId, Money money, Car car, List<ParkingTicket> tickets) {
        this(userId, money, car, ANONYMOUS, tickets);
    }

    public User(String userId, Money money, Car car, Membership membership) {
        this(userId, money, car, membership, null);
    }

    public User(String userId, Money money, Car car) {
        this(userId, money, car, null, null);
    }

    public User(String userId, Money money, Car car, Membership membership,
                List<ParkingTicket> tickets) {
        this.userId = userId;
        this.money = money;
        this.car = car;
        this.membership = membership;
        this.tickets = tickets;
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

    public List<ParkingTicket> getTickets() {
        return tickets;
    }

    public ParkingFee useParkingTicket(ParkingTicket parkingTicket) {
        return null;
    }
}
