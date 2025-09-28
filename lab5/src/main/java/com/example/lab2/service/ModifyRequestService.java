package com.example.lab2.service;

import org.springframework.stereotype.Service;
import com.example.lab2.model.Request;

@Service
public interface ModifyRequestService {
    void modify(Request request);
}
