package com.lucas.gowatch.usecase;

import com.lucas.gowatch.entity.Subscribe;
import com.lucas.gowatch.gateway.SubscribeChannelGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscribeChannelUseCase {

    @Autowired
    SubscribeChannelGateway gateway;

    public String execute(Subscribe subscribe){
        String response;
        if(subscribe.getChannel() == subscribe.getSubscribed()) throw new IllegalArgumentException("Not possible to subscribe at your channel!");

        // If already subscribed, toggle between subscribe and unsubscribe
        if(gateway.isSubscribe(subscribe))
            response = gateway.subscribe(subscribe);
        else
            response = gateway.unsubscribe(subscribe);

        return response;
    }
}
