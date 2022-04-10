package com.nhnacademy.pms.tdd.repository;

import com.nhnacademy.pms.tdd.Car;
import com.nhnacademy.pms.tdd.User;

public interface UserRepository {
    void insert(User user);

    User findByCar(Car car);
}
