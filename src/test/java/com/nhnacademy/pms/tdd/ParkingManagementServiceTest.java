package com.nhnacademy.pms.tdd;

import static com.nhnacademy.pms.tdd.Car.CarType.COMPACT;
import static com.nhnacademy.pms.tdd.Money.Currency.WON;
import static com.nhnacademy.pms.tdd.ParkingSpace.ParkingSpaceCode.A1;
import static com.nhnacademy.pms.tdd.ParkingSpace.ParkingSpaceCode.valueOf;
import static com.nhnacademy.pms.tdd.User.Membership.PAYCO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.pms.tdd.exception.NotEnoughMoneyException;
import com.nhnacademy.pms.tdd.repository.ParkingLotRepository;
import com.nhnacademy.pms.tdd.service.ParkingManagementService;
import com.nhnacademy.pms.tdd.service.Receipt;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ParkingManagementServiceTest {
    private ParkingManagementService service;

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
        service.parkAt(new ParkingSpace(A1, new Car(licenseNumber, COMPACT), LocalDateTime.now()));

        ParkingSpace space = new ParkingSpace(valueOf("A1"), new Car(licenseNumber, COMPACT),
            LocalDateTime.now());
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
        Car car = new Car(licenseNumber, COMPACT);

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

    @DisplayName("[1] 주차장에서 차가 나갈 때 주차 시간만큼 결제를 진행한다(2022년 4월 9일 16시 정각 주차).")
    @ParameterizedTest
    @ValueSource(strings = {"2022-04-09T16:30:00", "2022-04-09T16:40:00", "2022-04-10T16:10:00"})
    void exit_payParkingFee(String parkingTime) {
        String licenseNumber = "34조5789";
        Car car = new Car(licenseNumber, COMPACT);

        Money money = spy(new Money(10_000L, WON));
        User user = spy(new User("CoRock", money, car));
        ParkingSpace space = spy(new ParkingSpace(A1, car,
            LocalDateTime.of(LocalDate.of(2022, 4, 9), LocalTime.of(16, 0, 0))));

        when(repository.findParkingSpaceByLicenseNumber(licenseNumber)).thenReturn(space);
        when(repository.findUserByParkingSpaceCar(space)).thenReturn(user);

        service.parkAt(space);

        List<Integer> dateTimeSources = parse(parkingTime);
        LocalDateTime parkingDateTime = LocalDateTime.of(
            LocalDate.of(dateTimeSources.get(0), dateTimeSources.get(1), dateTimeSources.get(2)),
            LocalTime.of(dateTimeSources.get(3), dateTimeSources.get(4), dateTimeSources.get(5)));

        assertThat(service.exit(car, parkingDateTime))
            .isNotNull()
            .isNotEmpty();

        verify(repository, times(1)).findUserByParkingSpaceCar(space);
    }

    static List<Integer> parse(String parkingTime) {
        String[] dateTimeSources = parkingTime.split("-|T|:");
        List<Integer> parsingDateTimeSources = new ArrayList<>();
        for (String dateTimeSource : dateTimeSources) {
            parsingDateTimeSources.add(Integer.parseInt(dateTimeSource));
        }
        return parsingDateTimeSources;
    }

    @DisplayName("사용자가 PAYCO 회원인지 확인한다.")
    @Test
    public void pay_ifUserIsPaycoMembership_thenDiscountTenPercentParkingFee() {
        String licenseNumber = "34조5789";
        Car car = new Car(licenseNumber, COMPACT);
        User user = spy(new User("CoRock", new Money(13_500L, WON), car, PAYCO));

        LocalDateTime startParkingTime = createLocalDateTime(2022, 4, 9, 16, 0, 0);
        ParkingSpace space = new ParkingSpace(A1, car, startParkingTime);

        when(repository.findParkingSpaceByLicenseNumber(licenseNumber)).thenReturn(space);
        when(repository.findUserByParkingSpaceCar(space)).thenReturn(user);

        assertThat(repository.findUserByParkingSpaceCar(space))
            .isNotNull()
            .isInstanceOf(User.class);
        assertThat(repository.findUserByParkingSpaceCar(space).getMembership()).isEqualTo(PAYCO);
    }

    private LocalDateTime createLocalDateTime(int year, int month, int dayOfMonth, int hour,
                                              int minute, int second) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
    }
}
