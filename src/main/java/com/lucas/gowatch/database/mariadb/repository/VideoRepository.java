package com.lucas.gowatch.database.mariadb.repository;

import com.lucas.gowatch.database.mariadb.model.Video;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<Video, Long> {
}
