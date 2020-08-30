package com.lucas.gowatch.gateway;

import com.lucas.gowatch.entity.Subscribe;

public interface SubscribeChannelGateway {
    String subscribe(Subscribe subscribe);
    String unsubscribe(Subscribe subscribe);
    Boolean isSubscribe(Subscribe subscribe);
}
