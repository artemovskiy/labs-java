package com.example.lab2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.example.lab2.model.Response;
import com.example.lab2.util.DateTimeUtil;
import java.util.Date;

@Slf4j
@Service
@Qualifier("ModifySystemTimeResponseService")
public class ModifySystemTimeResponseService implements ModifyResponseService {

    @Override
    public Response modify(Response response) {
        String oldSystemTime = response.getSystemTime();
        response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
        log.info("Поле systemTime изменено с '{}' на '{}'", oldSystemTime, response.getSystemTime());
        return response;
    }
}

