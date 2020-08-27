package com.lucas.gowatch.gateway.Impl;

import com.lucas.gowatch.controller.mapper.Translator;
import com.lucas.gowatch.gateway.CreateChannelGataway;
import com.lucas.gowatch.entity.Channel;
import com.lucas.gowatch.gateway.mariadb.model.ChannelDBDomain;
import com.lucas.gowatch.gateway.mariadb.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateChannelGatawayImpl implements CreateChannelGataway {

    @Autowired
    private ChannelRepository repository;

    @Override
    public Optional<Channel> execute(Channel channel) {
        ChannelDBDomain channelDBDomain = repository.save(Translator.translate(channel, ChannelDBDomain.class));
        Channel channelOut = Translator.translate(channelDBDomain, Channel.class);
        return Optional.of(channelOut);
    }
}
