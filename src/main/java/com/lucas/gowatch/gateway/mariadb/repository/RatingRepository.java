package com.lucas.gowatch.gateway.mariadb.repository;

import com.lucas.gowatch.gateway.mariadb.model.ChannelDBDomain;
import com.lucas.gowatch.gateway.mariadb.model.RatingDBDomain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RatingRepository extends CrudRepository<RatingDBDomain, Long> {
    @Query(value = "select * from rating where channel_id = :channel", nativeQuery = true)
    Optional<RatingDBDomain> findByChannel(@Param("channel") ChannelDBDomain channel);

    @Query(value = "select * from rating where channel_id = :channel and video_id = :video", nativeQuery = true)
    Optional<RatingDBDomain> findByVideoAndChannel(@Param("video") Long video, @Param("channel") Long channel);

    // Delete rating by channel
    @Query(value = "delete from rating where channel_id = :channel", nativeQuery = true)
    void deleteByChannel(@Param("channel") ChannelDBDomain channel);
}
