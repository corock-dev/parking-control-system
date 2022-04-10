package com.nhnacademy.pms.tdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.pms.tdd.exception.CannotBeParkedCarTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EntranceMockTest {
    private Entrance entrance;

    @BeforeEach
    void setUp() {
        entrance = mock(Entrance.class);
    }

    @DisplayName("[1] 주차장에 차가 들어오면 번호판을 인식한다.")
    @Test
    void scan_licensePlate_forACar() {
        Car car = new Car("34조5789");
        when(entrance.scanLicensePlateNumber(car)).thenReturn(car.getLicenseNumber());

        assertThat(entrance.scanLicensePlateNumber(car)).isNotNull();

        verify(entrance).scanLicensePlateNumber(car);
    }

    @DisplayName("[3] 주차장에 대형차는 주차할 수 없다.")
    @Test
    void canLargeCarBeParked() {
        Car truck = mock(LargeCar.class);

        doThrow(new CannotBeParkedCarTypeException("The large car cannot be parked")).when(entrance).scanCarType(truck);

        assertThat(truck).isNotNull().isInstanceOf(LargeCar.class);
        assertThatThrownBy(() -> entrance.scanCarType(truck))
            .isInstanceOf(CannotBeParkedCarTypeException.class)
            .hasMessageContainingAll("large car", "cannot be parked");
    }
}
