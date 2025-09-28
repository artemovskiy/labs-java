package com.example.lab2.service;

import com.example.lab2.model.Positions;
import org.springframework.stereotype.Service;

@Service
public class QuarterlyBonusServiceImpl implements QuarterlyBonusService {

    public double calculateQuarterlyBonus(Positions position, double salary, double bonus, int workDays) {
        if (!position.isManager()) {
            throw new IllegalArgumentException("Квартальная премия доступна только для менеджеров");
        }

        return salary * bonus * 91.25 * position.getPositionCoefficient() / workDays;
    }
}

