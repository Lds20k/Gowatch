package com.lucas.gowatch.gateway.implementation;

import com.lucas.gowatch.gateway.DeleteChannelGateway;
import com.lucas.gowatch.gateway.mariadb.model.ChannelDBDomain;
import com.lucas.gowatch.gateway.mariadb.model.VideoDBDomain;
import com.lucas.gowatch.gateway.mariadb.repository.ChannelRepository;
import com.lucas.gowatch.gateway.mariadb.repository.RatingRepository;
import com.lucas.gowatch.gateway.mariadb.repository.VideoRepository;
import com.lucas.gowatch.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class DeleteChannelGatewayImplementation implements DeleteChannelGateway {

    @Autowired
    ChannelRepository repository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    StorageService storageService;

    @Override
    public String execute(Long id) {
        ChannelDBDomain channelDBDomain = repository.findById(id).get();
        repository.deleteSubscriptions(channelDBDomain);
        repository.deleteSubscribers(channelDBDomain);

        ratingRepository.deleteByChannel(channelDBDomain);
        
        // Delete video files
        videoRepository.findByChannel(channelDBDomain).get().forEach(new Consumer<VideoDBDomain>() {
            @Override
            public void accept(VideoDBDomain videoDBDomain) {
                System.out.println("Deleting file " + videoDBDomain.getVideoFile());
                if(storageService.delete(videoDBDomain.getVideoFile()))
                    System.out.println("File " + videoDBDomain.getVideoFile() + " was deleted!");
            }
        });
        
        // Delete video from DB
        videoRepository.deleteByChannel(channelDBDomain);

        repository.delete(channelDBDomain);
        return "Channel was deleted!";
    }
}
