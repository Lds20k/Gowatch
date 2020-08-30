package com.lucas.gowatch.gateway.implementation;

import com.lucas.gowatch.gateway.DeleteVideoGateway;
import com.lucas.gowatch.gateway.mariadb.model.VideoDBDomain;
import com.lucas.gowatch.gateway.mariadb.repository.RatingRepository;
import com.lucas.gowatch.gateway.mariadb.repository.VideoRepository;
import com.lucas.gowatch.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteVideoGatewayImplementation implements DeleteVideoGateway {

    @Autowired
    VideoRepository repository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    StorageService storageService;

    // Delete video from db and storage
    @Override
    public String execute(Long id) {
        VideoDBDomain videoDBDomain = repository.findById(id).get();
        ratingRepository.deleteByVideoId(id);

        storageService.delete(videoDBDomain.getVideoFile());
        repository.deleteById(id);

        return "Video was deleted!";
    }
}
