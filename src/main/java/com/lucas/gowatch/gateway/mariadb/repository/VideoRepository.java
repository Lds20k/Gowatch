package com.lucas.gowatch.gateway.mariadb.repository;

import com.lucas.gowatch.gateway.mariadb.model.ChannelDBDomain;
import com.lucas.gowatch.gateway.mariadb.model.VideoDBDomain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VideoRepository extends CrudRepository<VideoDBDomain, Long> {
    @Query(value = "select * from video where channel_id = :channel", nativeQuery = true)
    Optional< Iterable<VideoDBDomain> > findByChannel(@Param("channel") ChannelDBDomain channel);

    // Increment rating like counter
    @Query(value = "update video set likes = likes + 1 where id = :video", nativeQuery = true)
    void incrementLike(@Param("video") Long video);

    // Increment rating like counter
    @Query(value = "update video set likes = likes - 1 where id = :video", nativeQuery = true)
    void decrementRatingLike(@Param("video") Long video);

    // Increment rating dislike counter
    @Query(value = "update video set dislikes = dislikes + 1 where id = :video", nativeQuery = true)
    void incrementDislike(@Param("video") Long video);

    // Decrement rating dislike counter
    @Query(value = "update video set dislikes = dislikes - 1 where id = :video", nativeQuery = true)
    void decrementRatingDislike(@Param("video") Long video);

    // Delete rating by channel
    @Query(value = "delete from video where channel_id = :channel", nativeQuery = true)
    void deleteByChannel(@Param("channel") ChannelDBDomain channelDBDomain);
}
