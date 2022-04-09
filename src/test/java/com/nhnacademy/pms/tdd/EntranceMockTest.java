package com.nhnacademy.pms.tdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
}
