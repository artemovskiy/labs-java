package com.example.lab2.service;


import org.springframework.stereotype.Service;
import com.example.lab2.model.Positions;

@Service
public interface AnnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays);
}
