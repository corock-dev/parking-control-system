package com.nhnacademy.pms.tdd;

import static com.nhnacademy.pms.tdd.Money.Currency.WON;
import static com.nhnacademy.pms.tdd.ParkingSpace.ParkingSpaceCode.A1;
import static com.nhnacademy.pms.tdd.ParkingSpace.ParkingSpaceCode.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.pms.tdd.exception.NotEnoughMoneyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParkingManagementServiceTest {
    // SUT
    private ParkingManagementService service;

    // DOC
    private ParkingLotRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(ParkingLotRepository.class);
        service = new ParkingManagementService(repository);
    }

    @DisplayName("[1] 차를 A-1 주차 구역에 주차한다.")
    @Test
    void parkAt_specificParkingSpace() {
        String licenseNumber = "34조5789";
        service.parkAt(new ParkingSpace(A1, new Car(licenseNumber)));

        ParkingSpace space = new ParkingSpace(valueOf("A1"), new Car(licenseNumber));
        when(repository.findParkingSpaceByLicenseNumber(licenseNumber)).thenReturn(space);

        assertThat(repository.findParkingSpaceByLicenseNumber(licenseNumber))
            .isNotNull()
            .isInstanceOf(ParkingSpace.class);

        verify(repository, times(1)).findParkingSpaceByLicenseNumber(licenseNumber);
    }

    @DisplayName("[1] 주차장에서 차가 나갈 때 만약 돈이 없으면 나갈 수 없다.")
    @Test
    void exit_parkingLotWhenUserHasNoMoney_throwNotEnoughMoneyException() {
        String licenseNumber = "34조5789";
        Car car = new Car(licenseNumber);

        Money money = spy(new Money(999L, WON));
        User user = spy(new User("CoRock", money, car));
        ParkingSpace space = mock(ParkingSpace.class);

        when(repository.findParkingSpaceByLicenseNumber(licenseNumber)).thenReturn(space);
        when(repository.findUserByParkingSpaceCar(space)).thenReturn(user);

        assertThatThrownBy(() -> service.pay(car))
            .isInstanceOf(NotEnoughMoneyException.class)
            .hasMessageContainingAll("not enough money", user.getUserId(), car.getLicenseNumber());

        verify(repository, times(1)).findParkingSpaceByLicenseNumber(licenseNumber);
        verify(repository, times(1)).findUserByParkingSpaceCar(space);
    }
}
