package com.lucas.gowatch.gateway.implementation;

import com.lucas.gowatch.controller.mapper.Translator;
import com.lucas.gowatch.entity.Video;
import com.lucas.gowatch.gateway.UpdateVideoGateway;
import com.lucas.gowatch.gateway.mariadb.model.VideoDBDomain;
import com.lucas.gowatch.gateway.mariadb.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateVideoGatewayImplementation implements UpdateVideoGateway {

    @Autowired
    private VideoRepository repository;

    @Override
    public Video execute(Video video) {
        if(video.getId() == null) throw new IllegalArgumentException("Id is empty!");
        VideoDBDomain videoDBDomain = repository.findById(video.getId()).get();

        videoDBDomain.setTitle(video.getTitle());
        videoDBDomain.setDescription(video.getDescription());
        
        return Translator.translate(repository.save(videoDBDomain), Video.class);
    }
}
