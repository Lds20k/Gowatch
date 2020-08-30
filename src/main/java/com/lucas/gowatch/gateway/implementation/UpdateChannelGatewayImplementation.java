package com.lucas.gowatch.gateway.implementation;

import com.lucas.gowatch.controller.mapper.Translator;
import com.lucas.gowatch.entity.Channel;
import com.lucas.gowatch.gateway.UpdateChannelGateway;
import com.lucas.gowatch.gateway.mariadb.model.ChannelDBDomain;
import com.lucas.gowatch.gateway.mariadb.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateChannelGatewayImplementation implements UpdateChannelGateway {

    @Autowired
    private ChannelRepository repository;

    @Override
    public Channel execute(Channel channel) {
        if(channel.getId() == null) throw new IllegalArgumentException("Id is empty!");
        ChannelDBDomain channelDBDomain = repository.findById(channel.getId()).get();

        channelDBDomain.setUsername(channel.getUsername());
        channelDBDomain.setEmail(channel.getEmail());
        channelDBDomain.setAbout(channel.getAbout());
        channelDBDomain.setLocation(channel.getLocation());

        return Translator.translate(repository.save(channelDBDomain), Channel.class);
    }
}
