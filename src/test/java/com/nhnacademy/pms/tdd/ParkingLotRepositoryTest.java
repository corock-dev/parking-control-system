package com.nhnacademy.pms.tdd;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.nhnacademy.pms.tdd.repository.ParkingLotRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;

class ParkingLotRepositoryTest {
    private ParkingLotRepository repository;

    @Spy
    private List<Entrance> entrances;

    @Spy
    private List<Exit> exits;

    @BeforeEach
    void setUp() {
        entrances = new ArrayList<>();
        exits = new ArrayList<>();
        repository = new ParkingLotRepository(entrances, exits, null);
    }

    @DisplayName("[2] 주차장 입구가 n개 이상이다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 3})
    void isMultipleEntrancesAtParkingLot(int count) {
        for (int i = 0; i < count; i++) {
            entrances.add(mock(Entrance.class));
        }

        assertTrue(entrances.size() >= 2);
    }

    @DisplayName("[2] 주차장 출구가 n개 이상이다.")
    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6})
    void isMultipleExitsAtParkingLot(int count) {
        for (int i = 0; i < count; i++) {
            exits.add(mock(Exit.class));
        }

        assertTrue(exits.size() >= 2);
    }
}
