package com.lucas.gowatch.usecase;

import com.lucas.gowatch.entity.Channel;
import com.lucas.gowatch.gateway.ConsultOneChannelGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultOneChannelUseCase {

    @Autowired
    private ConsultOneChannelGateway gateway;

    public Channel execute(Long id){
        return gateway.execute(id);
    }
}
