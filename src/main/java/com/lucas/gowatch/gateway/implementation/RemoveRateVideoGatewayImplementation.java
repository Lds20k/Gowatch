package com.lucas.gowatch.gateway.implementation;

import com.lucas.gowatch.entity.Rating;
import com.lucas.gowatch.gateway.RemoveRateVideoGateway;
import com.lucas.gowatch.gateway.mariadb.model.RatingDBDomain;
import com.lucas.gowatch.gateway.mariadb.repository.RatingRepository;
import com.lucas.gowatch.gateway.mariadb.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveRateVideoGatewayImplementation implements RemoveRateVideoGateway {

    @Autowired
    RatingRepository repository;

    @Autowired
    VideoRepository videoRepository;

    // Remove rating and decrement like/dislike
    @Override
    public String execute(Rating rating) {
        RatingDBDomain ratingDBDomain = repository.findByVideoAndChannel(rating.getVideo(), rating.getChannel()).get();
        repository.delete(ratingDBDomain);

        if(ratingDBDomain.getType().equals("like")){
            videoRepository.decrementRatingLike(rating.getVideo());
        }else{
            videoRepository.decrementRatingDislike(rating.getVideo());
        }

        return "rating removed";
    }
}
