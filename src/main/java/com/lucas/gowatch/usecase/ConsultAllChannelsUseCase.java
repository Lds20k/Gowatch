package com.lucas.gowatch.usecase;

import com.lucas.gowatch.entity.Channel;
import com.lucas.gowatch.gateway.ConsultAllChannelsGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultAllChannelsUseCase {

    @Autowired
    private ConsultAllChannelsGateway gateway;

    public Iterable<Channel> execute(){
        return gateway.execute();
    }
}
