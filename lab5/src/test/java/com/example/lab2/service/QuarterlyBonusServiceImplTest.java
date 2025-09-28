package com.example.lab2.service;

import com.example.lab2.model.Positions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuarterlyBonusServiceImplTest {

    @Test
    void calculateQuarterlyBonus() {
        QuarterlyBonusServiceImpl service = new QuarterlyBonusServiceImpl();

        double result = service.calculateQuarterlyBonus(Positions.MANAGER, 150000, 0.2, 60);
        assertEquals(136875.0, result, 0.01);

        assertThrows(IllegalArgumentException.class,
                () -> service.calculateQuarterlyBonus(Positions.DEV, 100000, 0.1, 60));
    }
}
