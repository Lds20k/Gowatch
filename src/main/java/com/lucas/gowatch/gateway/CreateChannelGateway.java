package com.lucas.gowatch.gateway;

import com.lucas.gowatch.entity.Channel;

import java.util.Optional;

public interface CreateChannelGateway {
    Optional<Channel> execute(Channel channel);
}
