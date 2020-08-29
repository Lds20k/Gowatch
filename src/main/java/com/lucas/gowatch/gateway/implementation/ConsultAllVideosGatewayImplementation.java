package com.lucas.gowatch.gateway.implementation;

import com.lucas.gowatch.controller.mapper.Translator;
import com.lucas.gowatch.entity.Video;
import com.lucas.gowatch.gateway.ConsultAllVideosGateway;
import com.lucas.gowatch.gateway.mariadb.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultAllVideosGatewayImplementation implements ConsultAllVideosGateway {

    @Autowired
    VideoRepository repository;

    @Override
    public Iterable<Video> execute() {
        return Translator.translate(repository.findAll(), Video.class);
    }
}
