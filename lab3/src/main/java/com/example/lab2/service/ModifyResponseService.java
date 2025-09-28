package com.example.lab2.service;


import org.springframework.stereotype.Service;
import com.example.lab2.model.Response;

@Service
public interface ModifyResponseService {

    Response modify(Response response);
}
