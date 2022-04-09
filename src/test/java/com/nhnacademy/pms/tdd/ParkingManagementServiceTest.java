package com.nhnacademy.pms.tdd;

import static com.nhnacademy.pms.tdd.ParkingSpace.ParkingSpaceCode.A1;
import static com.nhnacademy.pms.tdd.ParkingSpace.ParkingSpaceCode.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        service.parkAt(new ParkingSpace(A1, new Car("34조5789")));

        ParkingSpace space = new ParkingSpace(valueOf("A1"), new Car("34조5789"));
        when(repository.findCarByLicenseNumber("34조5789")).thenReturn(space);

        assertThat(repository.findCarByLicenseNumber("34조5789")).isNotNull();

        verify(repository, times(1)).findCarByLicenseNumber("34조5789");
    }
}
