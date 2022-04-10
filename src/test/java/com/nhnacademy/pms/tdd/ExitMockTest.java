package com.nhnacademy.pms.tdd;

import static com.nhnacademy.pms.tdd.Car.CarType.COMPACT;
import static com.nhnacademy.pms.tdd.Money.Currency.WON;
import static com.nhnacademy.pms.tdd.ParkingManagementServiceTest.parse;
import static com.nhnacademy.pms.tdd.ParkingSpace.ParkingSpaceCode.A1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ExitMockTest {
    private Exit exit;

    @BeforeEach
    void setUp() {
        exit = mock(Exit.class);
    }

    @DisplayName("[3] 변경된 요금표대로 요금이 측정되는 지 테스트(2022년 4월 9일 16시 정각 주차).")
    @ParameterizedTest
    @ValueSource(strings = {"2022-04-09T16:30:00", "2022-04-09T16:40:00", "2022-04-09T16:50:00",
        "2022-04-10T16:10:00", "2022-04-11T16:10:00"})
    void pay_isReflectedRevisedParkingFeePolicy(String parkingTime) {
        String licenseNumber = "34조5789";
        Car car = new Car(licenseNumber, COMPACT);

        Money money = spy(new Money(10_000L, WON));
        User user = spy(new User("CoRock", money, car));
        ParkingSpace space = spy(new ParkingSpace(A1, car,
            LocalDateTime.of(LocalDate.of(2022, 4, 9), LocalTime.of(16, 0, 0))));

        ParkingFee parkingFee = mock(ParkingFee.class);
        when(exit.pay(car)).thenReturn(parkingFee);

        List<Integer> dateTimeSources = parse(parkingTime);
        LocalDateTime parkingDateTime = LocalDateTime.of(
            LocalDate.of(dateTimeSources.get(0), dateTimeSources.get(1), dateTimeSources.get(2)),
            LocalTime.of(dateTimeSources.get(3), dateTimeSources.get(4), dateTimeSources.get(5)));

        assertThat(exit.pay(car))
            .isNotNull()
            .isInstanceOf(ParkingFee.class);

        verify(exit, times(1)).pay(car);
    }

    @DisplayName("[3] 경차의 경우 요금이 50% 감면된다.")
    @Test
    void pay_isAvailableHalfPriceForCompactCar() {
        Car car = new Car("34조5789", COMPACT);
        LocalDateTime startParkingTime =
            LocalDateTime.of(LocalDate.of(2022, 4, 9), LocalTime.of(16, 0, 0));
        ParkingSpace space = new ParkingSpace(A1, car, startParkingTime);

        LocalDateTime endParkingTime =
            LocalDateTime.of(LocalDate.of(2022, 4, 9), LocalTime.of(16, 40, 0));
        ParkingFee parkingFee = new HalfPastParkingFee(new Money(500L, WON));
        when(exit.pay(car, endParkingTime)).thenReturn(parkingFee);

        assertThat(exit.pay(car, endParkingTime)).isEqualTo(parkingFee);

        verify(exit, times(1)).pay(car, endParkingTime);
    }
}
