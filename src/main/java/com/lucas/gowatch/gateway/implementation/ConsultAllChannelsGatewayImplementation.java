package com.lucas.gowatch.gateway.implementation;

import com.lucas.gowatch.controller.mapper.Translator;
import com.lucas.gowatch.entity.Channel;
import com.lucas.gowatch.gateway.ConsultAllChannelsGateway;
import com.lucas.gowatch.gateway.mariadb.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultAllChannelsGatewayImplementation implements ConsultAllChannelsGateway {

    @Autowired
    private ChannelRepository repository;

    @Override
    public Iterable<Channel> execute() {
        return Translator.translate(repository.findAll(), Channel.class);
    }
}
