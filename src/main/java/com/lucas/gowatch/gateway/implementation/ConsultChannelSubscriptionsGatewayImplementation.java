package com.lucas.gowatch.gateway.implementation;

import com.lucas.gowatch.controller.mapper.Translator;
import com.lucas.gowatch.entity.Channel;
import com.lucas.gowatch.gateway.ConsultChannelSubscriptionsGateway;
import com.lucas.gowatch.gateway.mariadb.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultChannelSubscriptionsGatewayImplementation implements ConsultChannelSubscriptionsGateway {

    @Autowired
    ChannelRepository repository;

    @Override
    public Iterable<Channel> execute(Long id) {
        return Translator.translate(repository.findChannelSubscription(id), Channel.class);
    }
}
