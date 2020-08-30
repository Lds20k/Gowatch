package com.lucas.gowatch.usecase;

import com.lucas.gowatch.entity.Channel;
import com.lucas.gowatch.gateway.UpdateChannelGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateChannelUseCase {

    @Autowired
    private UpdateChannelGateway gateway;

    public Channel execute(Channel channel) {
        return gateway.execute(channel);
    }
}
