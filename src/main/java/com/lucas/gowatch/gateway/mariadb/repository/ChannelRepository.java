package com.lucas.gowatch.gateway.mariadb.repository;


import com.lucas.gowatch.gateway.mariadb.model.ChannelDBDomain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ChannelRepository extends CrudRepository<ChannelDBDomain, Long> {
    // Create a entity instance, this subscribe channel to another
    // Its a self-relationship implementation
    @Query(
            value = "insert into subscribed (channel_id, subscribed_channel_id) " +
                    "values (:channelId, :subscribedChannelId)",
            nativeQuery = true
    )
    void insertSubscribed(@Param("channelId") ChannelDBDomain channelId, @Param("subscribedChannelId") ChannelDBDomain subscribedChannelId);

    @Query(
            value = "select channel.* from subscribed inner join channel on subscribed.subscribed_channel_id = channel.id where subscribed.channel_id = :channel",
            nativeQuery = true
    )
    Set<ChannelDBDomain> findSubscribedChannels(@Param("channel") ChannelDBDomain channel);

    @Query(value = "select * from channel where email = :email", nativeQuery = true)
    ChannelDBDomain findByEmail(@Param("email") String email);
}
