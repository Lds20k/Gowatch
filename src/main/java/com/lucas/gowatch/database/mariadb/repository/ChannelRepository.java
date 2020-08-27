package com.lucas.gowatch.database.mariadb.repository;


import com.lucas.gowatch.database.mariadb.model.Channel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ChannelRepository extends CrudRepository<Channel, Long> {
    // Create a entity instance, this subscribe channel to another
    // Its a self-relationship implementation
    @Query(
            value = "insert into subscribed (channel_id, subscribed_channel_id) " +
                    "values (:channelId, :subscribedChannelId)",
            nativeQuery = true
    )
    void insertSubscribed(@Param("channelId") Channel channelId, @Param("subscribedChannelId") Channel subscribedChannelId);

    @Query(
            value = "select channel.* from subscribed inner join channel on subscribed.subscribed_channel_id = channel.id where subscribed.channel_id = :channel",
            nativeQuery = true
    )
    Set<Channel> findSubscribedChannels(@Param("channel") Channel channel);

    @Query(value = "select * from channel where email = :email", nativeQuery = true)
    Channel findByEmail(@Param("email") String email);
}
