package com.example.lab2.service;


import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import com.example.lab2.exception.ValidationFailedException;
import com.example.lab2.exception.UnsupportedCodeException;
import com.example.lab2.model.Request;

@Service
public class RequestValidationService implements ValidationService {
    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }
    @Override
    public void validateUid(Request request) throws UnsupportedCodeException {
        if ("123".equals(request.getUid())) {
            throw new UnsupportedCodeException("uid не может быть равен 123");
        }
    }
}
