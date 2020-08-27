package com.lucas.gowatch.database.mariadb.repository;

import com.lucas.gowatch.database.mariadb.model.Channel;
import com.lucas.gowatch.database.mariadb.model.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RatingRepository extends CrudRepository<Rating, Long> {
    @Query(value = "select * from rating where channel_id = :channel", nativeQuery = true)
    Optional<Rating> findByChannel(@Param("channel") Channel channel);
}
