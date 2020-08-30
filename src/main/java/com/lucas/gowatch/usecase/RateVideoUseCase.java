package com.lucas.gowatch.usecase;

import com.lucas.gowatch.entity.Rating;
import com.lucas.gowatch.gateway.RateVideoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RateVideoUseCase {

    @Autowired
    RateVideoGateway gateway;

    public String execute(Rating rating) {
        return gateway.execute(rating);
    }
}
