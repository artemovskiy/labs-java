package com.example.lab2.service;

import com.example.lab2.exception.UnsupportedCodeException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import com.example.lab2.exception.ValidationFailedException;
import com.example.lab2.model.Request;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
    void validateUid(Request request) throws UnsupportedCodeException;
}
