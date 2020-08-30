package com.lucas.gowatch.usecase;

import com.lucas.gowatch.entity.Video;
import com.lucas.gowatch.gateway.UpdateVideoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateVideoUseCase {

    @Autowired
    UpdateVideoGateway gateway;

    public Video execute(Video video) {
        return gateway.execute(video);
    }
}
