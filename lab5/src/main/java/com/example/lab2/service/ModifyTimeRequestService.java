package com.example.lab2.service;

import com.example.lab2.model.Request;
import com.example.lab2.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@Qualifier("ModifyTimeRequestService")
public class ModifyTimeRequestService implements ModifyRequestService {

    @Override
    public void modify(Request request) {
        String currentTime = DateTimeUtil.getCustomFormat().format(new Date());
        request.setSystemTime(currentTime);
        log.info("Время запроса установлено в request: {}", currentTime);
    }
}
