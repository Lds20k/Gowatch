package com.lucas.gowatch.gateway.implementation;

import com.lucas.gowatch.controller.mapper.Translator;
import com.lucas.gowatch.entity.Channel;
import com.lucas.gowatch.gateway.ConsultOneChannelGateway;
import com.lucas.gowatch.gateway.mariadb.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultOneChannelGatewayImplementation implements ConsultOneChannelGateway {

    @Autowired
    private ChannelRepository repository;

    public Channel execute(Long id) {
        return Translator.translate(repository.findById(id).get(), Channel.class);
    }
}
