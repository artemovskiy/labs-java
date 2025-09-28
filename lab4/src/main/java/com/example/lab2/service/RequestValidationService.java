package com.example.lab2.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import com.example.lab2.exception.ValidationFailedException;
import com.example.lab2.exception.UnsupportedCodeException;
import com.example.lab2.model.Request;

@Slf4j
@Service
public class RequestValidationService implements ValidationService {
    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().toString();
            log.error("Ошибка валидации BindingResult. Выбрасывается ValidationFailedException: {}", errorMessage);
            throw new ValidationFailedException(bindingResult.getFieldError().toString());
        }
        log.info("Binding result корректен. Ошибок не найдено.");
    }
    @Override
    public void validateUid(Request request) throws UnsupportedCodeException {
        if ("123".equals(request.getUid())) {
            String errorMessage = "uid не может быть равен 123";
            log.error("Ошибка UnsupportedCodeException. Выбрасывается исключение, так как UID: {}", request.getUid());
            throw new UnsupportedCodeException("uid не может быть равен 123");
        }
    }
}
