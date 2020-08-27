package com.lucas.gowatch.gateway;

import com.lucas.gowatch.entity.Channel;

import java.util.Optional;

public interface CreateChannelGataway {
    public Optional<Channel> execute(Channel channel);
}
