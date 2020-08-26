package com.lucas.gowatch.database.mariadb.repository;


import com.lucas.gowatch.database.mariadb.model.Channel;
import org.springframework.data.repository.CrudRepository;

public interface ChannelRepository extends CrudRepository<Channel, Long> {
}
