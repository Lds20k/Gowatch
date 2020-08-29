package com.lucas.gowatch.usecase;

import com.lucas.gowatch.entity.Channel;
import com.lucas.gowatch.gateway.CreateChannelGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateChannelUsesCase {

    @Autowired
    private CreateChannelGateway gateway;

    public Channel execute(Channel channel) {
        return gateway.execute(channel);
    }
}
