package com.lucas.gowatch.gateway.implementation;

import com.lucas.gowatch.controller.mapper.Translator;
import com.lucas.gowatch.entity.Video;
import com.lucas.gowatch.gateway.ConsultOneVideoGateway;
import com.lucas.gowatch.gateway.mariadb.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultOneVideoGatewayImplementation implements ConsultOneVideoGateway {

    @Autowired
    VideoRepository repository;

    @Override
    public Video execute(Long id) {
        return Translator.translate(repository.findById(id).get(), Video.class);
    }
}
