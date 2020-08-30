package com.lucas.gowatch.gateway.implementation;

import com.lucas.gowatch.gateway.DeleteChannelGateway;
import com.lucas.gowatch.gateway.mariadb.model.ChannelDBDomain;
import com.lucas.gowatch.gateway.mariadb.repository.ChannelRepository;
import com.lucas.gowatch.gateway.mariadb.repository.RatingRepository;
import com.lucas.gowatch.gateway.mariadb.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteChannelGatewayImplementation implements DeleteChannelGateway {

    @Autowired
    ChannelRepository repository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    VideoRepository videoRepository;

    @Override
    public String execute(Long id) {
        ChannelDBDomain channelDBDomain = repository.findById(id).get();
        repository.deleteSubscriptions(channelDBDomain);
        repository.deleteSubscribers(channelDBDomain);

        ratingRepository.deleteByChannel(channelDBDomain);
        videoRepository.deleteByChannel(channelDBDomain);

        repository.delete(channelDBDomain);
        return "Channel was deleted!";
    }
}
