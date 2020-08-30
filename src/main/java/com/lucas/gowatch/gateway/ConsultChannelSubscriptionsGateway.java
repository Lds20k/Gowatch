package com.lucas.gowatch.gateway;

import com.lucas.gowatch.entity.Channel;

public interface ConsultChannelSubscriptionsGateway {
    Iterable<Channel> execute(Long id);
}
