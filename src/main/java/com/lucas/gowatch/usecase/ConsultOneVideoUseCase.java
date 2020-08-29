package com.lucas.gowatch.usecase;

import com.lucas.gowatch.entity.Video;
import com.lucas.gowatch.gateway.ConsultOneVideoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultOneVideoUseCase {

    @Autowired
    ConsultOneVideoGateway gateway;

    public Video execute(Long id){
        return gateway.execute(id);
    }
}
