package com.lucas.gowatch.database.mariadb.repository;

import com.lucas.gowatch.database.mariadb.model.Channel;
import com.lucas.gowatch.database.mariadb.model.Video;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VideoRepository extends CrudRepository<Video, Long> {
    @Query(value = "select * from video where channel_id = :channel", nativeQuery = true)
    Optional<Video> findByChannel(@Param("channel") Channel channel);
}
