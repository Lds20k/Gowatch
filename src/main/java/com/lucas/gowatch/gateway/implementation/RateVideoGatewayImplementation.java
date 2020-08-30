package com.lucas.gowatch.gateway.implementation;

import com.lucas.gowatch.entity.Rating;
import com.lucas.gowatch.gateway.RateVideoGateway;
import com.lucas.gowatch.gateway.mariadb.model.RatingDBDomain;
import com.lucas.gowatch.gateway.mariadb.repository.RatingRepository;
import com.lucas.gowatch.gateway.mariadb.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RateVideoGatewayImplementation implements RateVideoGateway {

    @Autowired
    RatingRepository repository;

    @Autowired
    VideoRepository videoRepository;

    /*
    Verify if channel already liked, increment/decrement likes and increment/decrement dislikes
    selected by rating type
    */
    @Override
    public String execute(Rating rating) {
        System.out.println(repository.findByChannelAndVideo(rating.getVideo(), rating.getChannel()));
        if(repository.findByChannelAndVideo(rating.getVideo(), rating.getChannel()).isEmpty()){
            repository.save(new RatingDBDomain(rating.getVideo(), rating.getChannel(), rating.getType()));

            if(rating.getType().equals("like"))
                videoRepository.incrementLike(rating.getVideo());
            else
                videoRepository.incrementDislike(rating.getVideo());

        }else{
            RatingDBDomain ratingDBDomain = repository.findByChannelAndVideo(rating.getVideo(), rating.getChannel()).get();
            if(!rating.getType().equals(ratingDBDomain.getType())){
                ratingDBDomain.setType(rating.getType());
                repository.save(ratingDBDomain);
                if(rating.getType().equals("like")){
                    videoRepository.incrementLike(rating.getVideo());
                    videoRepository.decrementRatingDislike(rating.getVideo());
                }
                else{
                    videoRepository.incrementDislike(rating.getVideo());
                    videoRepository.decrementRatingLike(rating.getVideo());
                }
            }
        }

        return rating.getType().equals("like") ? "liked" : "disliked";
    }
}
