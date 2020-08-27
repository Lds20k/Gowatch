package com.lucas.gowatch.usecase;

import com.lucas.gowatch.entity.Channel;
import com.lucas.gowatch.gateway.CreateChannelGataway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateChannelUsesCases{

    @Autowired
    private CreateChannelGataway channelGataway;

    public Channel execute(Channel channel) {
        return channelGataway.execute(channel).get();
    }
}
