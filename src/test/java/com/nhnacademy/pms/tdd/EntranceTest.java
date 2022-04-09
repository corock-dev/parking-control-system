package com.nhnacademy.pms.tdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EntranceTest {
    private Entrance entrance;

    @BeforeEach
    void setUp() {
        entrance = mock(Entrance.class);
    }

    @DisplayName("[1] 주차장에 차가 들어오면 번호판을 인식한다.")
    @Test
    void scanLicensePlate_aCar() {
        Car car = new Car("1234");
        when(entrance.scan(car)).thenReturn(car);

        assertThat(entrance.scan(car))
            .isNotNull()
            .isInstanceOf(Car.class);

        // verify(entrance.scan(car), times(1));
    }
}