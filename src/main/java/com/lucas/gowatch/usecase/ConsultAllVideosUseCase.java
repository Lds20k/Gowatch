package com.lucas.gowatch.usecase;

import com.lucas.gowatch.entity.Video;
import com.lucas.gowatch.gateway.ConsultAllVideosGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultAllVideosUseCase {

    @Autowired
    ConsultAllVideosGateway gateway;

    public Iterable<Video> execute(){
        return gateway.execute();
    }
}