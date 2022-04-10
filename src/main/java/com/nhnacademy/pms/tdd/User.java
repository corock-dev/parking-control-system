package com.nhnacademy.pms.tdd;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String userId;
    private final Money money;
    private final Car car;
    private final List<ParkingTicket> tickets;

    public User(String userId, Money money, Car car) {
        this(userId, money, car, new ArrayList<>());
    }

    public User(String userId, Money money, Car car,
                List<ParkingTicket> tickets) {
        this.userId = userId;
        this.money = money;
        this.car = car;
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

    public List<ParkingTicket> getTickets() {
        return tickets;
    }

    public ParkingFee useParkingTicket(TwoHourParkingTicket twoHourParkingTicket) {
        return null;
    }
}
