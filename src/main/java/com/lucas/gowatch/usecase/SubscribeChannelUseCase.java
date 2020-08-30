package com.lucas.gowatch.usecase;

import com.lucas.gowatch.entity.Subscribe;
import com.lucas.gowatch.gateway.SubscribeChannelGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public class SubscribeChannelUseCase {

    @Autowired
    SubscribeChannelGateway gateway;

    public String execute(Subscribe subscribe){
        String response;

        if(gateway.isSubscribe(subscribe))
            response = gateway.subscribe(subscribe);
        else
            response = gateway.unsubscribe(subscribe);

        //response = (gateway.isSubscribe(subscribe)) ? gateway.unsubscribe(subscribe) : gateway.subscribe(subscribe);
        return response;
    }
}
