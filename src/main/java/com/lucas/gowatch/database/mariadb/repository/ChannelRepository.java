package com.lucas.gowatch.database.mariadb.repository;


import com.lucas.gowatch.database.mariadb.model.Channel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ChannelRepository extends CrudRepository<Channel, Long> {
    // Create a entity instance, this subscribe channel to another
    // Its a self-relationship implementation
    @Query(
            value = "insert into subscribed (channel_id, subscribed_channel_id) " +
                    "values (:channelId, :subscribedChannelId)",
            nativeQuery = true
    )
    void insertSubiscribed(@Param("channelId") Channel channelId, @Param("subscribedChannelId") Channel subscribedChannelId);
}
