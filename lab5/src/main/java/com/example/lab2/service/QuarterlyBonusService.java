package com.example.lab2.service;


import com.example.lab2.model.Positions;

public interface QuarterlyBonusService {
    double calculateQuarterlyBonus(Positions position, double salary, double bonus, int workDays);
}
