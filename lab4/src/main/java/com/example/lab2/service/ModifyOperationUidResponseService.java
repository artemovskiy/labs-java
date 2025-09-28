package com.example.lab2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.example.lab2.model.Response;
import java.util.UUID;

@Slf4j
@Service
@Qualifier("ModifyOperationUidResponseService")
public class ModifyOperationUidResponseService implements ModifyResponseService {

    @Override
    public Response modify(Response response) {
        String oldOperationUid = response.getOperationUid();
        UUID uuid = UUID.randomUUID();
        response.setOperationUid(uuid.toString());
        log.info("Поле operationUid изменено с '{}' на '{}'", oldOperationUid, response.getOperationUid());
        return response;
    }
}


