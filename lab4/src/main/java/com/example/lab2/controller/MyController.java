package com.example.lab2.controller;

import com.example.lab2.exception.ValidationFailedException;
import com.example.lab2.exception.UnsupportedCodeException;
import com.example.lab2.model.*;
import com.example.lab2.service.ModifyResponseService;
import com.example.lab2.service.ValidationService;
import com.example.lab2.util.DateTimeUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;
import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;

    private final ModifyResponseService modifySystemTimeService;
    private final ModifyResponseService modifyOperationUidService;


    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifySystemTimeService,
                        @Qualifier("ModifyOperationUidResponseService") ModifyResponseService modifyOperationUidService
    ) {
        this.validationService = validationService;
        this.modifySystemTimeService = modifySystemTimeService;
        this.modifyOperationUidService = modifyOperationUidService;

    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {
        log.info("request: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
        log.info("Инициализация response: {}", response);
        try {
            log.info("Начало валидации");
            validationService.isValid(bindingResult);
            validationService.validateUid(request);
            log.info("Валидация прошла успешно");
        } catch (UnsupportedCodeException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);
            log.info("Response изменен после обработки UnsupportedCodeException: {}", response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (ValidationFailedException e) {
            log.error("Перехвачено исключение ValidationFailedException: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            log.info("Response изменен после обработки ValidationFailedException: {}", response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            log.info("Response изменен после обработки UnknownException: {}", response);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            Date initialRequestTime = DateTimeUtil.getCustomFormat().parse(request.getSystemTime());
            Date receivedModifiedTime = new Date();
            long timeDifference = receivedModifiedTime.getTime() - initialRequestTime.getTime();
            log.info("Время с момента отправки Postman до получения модифицированного запроса Сервисом 2: {} мс", timeDifference);
        } catch (ParseException e) {
            log.error("Не удалось разобрать поле systemTime для измерения разницы времени: {}", e.getMessage());
        }
        log.info("Начинаем модификацию response");
        modifySystemTimeService.modify(response);
        log.info("Response после ModifySystemTimeResponseService: {}", response);

        modifyOperationUidService.modify(response);
        log.info("Response после ModifyOperationUidResponseService: {}", response);
        log.info("Финальный response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
