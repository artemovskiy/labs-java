package com.example.lab2.service;

import org.springframework.stereotype.Service;
import com.example.lab2.model.Positions;
import java.time.Year;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {

    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        int daysInYear = Year.now().isLeap() ? 366 : 365;
        return salary * bonus * daysInYear * positions.getPositionCoefficient() / workDays;
    }
}
