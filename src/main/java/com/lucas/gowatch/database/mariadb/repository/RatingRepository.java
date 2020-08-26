package com.lucas.gowatch.database.mariadb.repository;

import com.lucas.gowatch.database.mariadb.model.Rating;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Long> {
}
