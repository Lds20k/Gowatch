package com.lucas.gowatch.gateway.implementation;

import com.lucas.gowatch.entity.Subscribe;
import com.lucas.gowatch.gateway.SubscribeChannelGateway;
import com.lucas.gowatch.gateway.mariadb.model.ChannelDBDomain;
import com.lucas.gowatch.gateway.mariadb.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SubscribeChannelGatewayImplementation implements SubscribeChannelGateway {

    @Autowired
    ChannelRepository repository;

    // Increment subscribers counter and insert in subscribed table
    @Override
    public String subscribe(Subscribe subscribe) {
        repository.incrementSubscribersCounter(subscribe.getSubscribed());
        repository.insertSubscribed(subscribe.getChannel(), subscribe.getSubscribed());
        return "subscribed";
    }

    // Decrement subscribers counter and delete from subscribed table
    @Override
    public String unsubscribe(Subscribe subscribe) {
        repository.decrementSubscribersCounter(subscribe.getSubscribed());
        repository.deleteSubscribed(subscribe.getChannel(), subscribe.getSubscribed());
        return "unsubscribed";
    }

    // Verify if is subscribed
    @Override
    public Boolean isSubscribe(Subscribe subscribe) {
        Optional<ChannelDBDomain> test = repository.findSubscription(subscribe.getChannel(), subscribe.getSubscribed());
        return test.isEmpty();
    }
}
