package com.lucas.gowatch.gateway.mariadb.repository;

import com.lucas.gowatch.gateway.mariadb.model.ChannelDBDomain;
import com.lucas.gowatch.gateway.mariadb.model.VideoDBDomain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VideoRepository extends CrudRepository<VideoDBDomain, Long> {
    @Query(value = "select * from video where channel_id = :channel", nativeQuery = true)
    Optional<VideoDBDomain> findByChannel(@Param("channel") ChannelDBDomain channel);
}
