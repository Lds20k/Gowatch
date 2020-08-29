package com.lucas.gowatch.gateway.implementation;

import com.lucas.gowatch.controller.mapper.Translator;
import com.lucas.gowatch.entity.Video;
import com.lucas.gowatch.gateway.CreateVideoGateway;
import com.lucas.gowatch.gateway.mariadb.model.VideoDBDomain;
import com.lucas.gowatch.gateway.mariadb.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CreateVideoGatewayImplementation implements CreateVideoGateway {

    @Autowired
    VideoRepository repository;

    @Override
    public Video execute(Video video) {
        video.setUploadDate(LocalDate.now());
        VideoDBDomain videoDBDomain = repository.save(Translator.translate(video, VideoDBDomain.class));
        return Translator.translate(videoDBDomain, Video.class);
    }
}
