package com.nhnacademy.pms.tdd;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EntranceNewTest {
    private Entrance entrance;

    @BeforeEach
    void setUp() {
        entrance = new Entrance();
    }

    @DisplayName("[1] 번호판을 인식할 수 없는 차가 주차장에 들어오면 예외를 발생한다.")
    @Test
    void scan_unknownLicensePlate_forACar() {
        Car car = new Car(null, null);

        assertThatIllegalArgumentException().isThrownBy(() -> entrance.scanLicensePlateNumber(car))
            .withMessageContaining("null");
    }
}
