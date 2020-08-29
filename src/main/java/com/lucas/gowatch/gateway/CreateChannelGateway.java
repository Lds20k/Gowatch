package com.lucas.gowatch.gateway;

import com.lucas.gowatch.entity.Channel;

public interface CreateChannelGateway {
    Channel execute(Channel channel);
}
