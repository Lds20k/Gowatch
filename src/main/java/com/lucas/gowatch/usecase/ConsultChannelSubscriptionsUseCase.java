package com.lucas.gowatch.usecase;

import com.lucas.gowatch.entity.Channel;
import com.lucas.gowatch.gateway.ConsultChannelSubscriptionsGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultChannelSubscriptionsUseCase {

    @Autowired
    ConsultChannelSubscriptionsGateway gateway;

    public Iterable<Channel> execute(Long id){
        return gateway.execute(id);
    }
}
