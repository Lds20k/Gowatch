package com.lucas.gowatch.gateway.mariadb.repository;

import com.lucas.gowatch.gateway.mariadb.model.ChannelDBDomain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
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
            value = "insert into subscribed (channel_id, subscribed_channel_id) " +
                    "values (:channelId, :subscribedChannelId)",
            nativeQuery = true
    )
    void insertSubscribed(@Param("channelId") Long channelId, @Param("subscribedChannelId") Long subscribedChannelId);

    // Delete subscribed
    @Query(
            value = "delete from subscribed where channel_id = :channelId and subscribed_channel_id = :subscribedChannelId",
            nativeQuery = true
    )
    void deleteSubscribed(@Param("channelId") Long channel, @Param("subscribedChannelId") Long subscribeChannel);

    // Increment subscribers counter
    @Query(
            value = "update channel set subscribers_counter = subscribers_counter + 1 where id = :channelId",
            nativeQuery = true
    )
    void incrementSubscribersCounter(@Param("channelId") Long channel);

    // Decrement subscribers counter
    @Query(
            value = "update channel set subscribers_counter = subscribers_counter - 1 where id = :channelId",
            nativeQuery = true
    )
    void decrementSubscribersCounter(@Param("channelId") Long channel);

    // Find all channels that this channel is subscribed
    @Query(
            value = "select channel.* from subscribed inner join channel on subscribed.subscribed_channel_id = channel.id where subscribed.channel_id = :channel",
            nativeQuery = true
    )
    Set<ChannelDBDomain> findChannelSubscription(@Param("channel") ChannelDBDomain channel);
    @Query(
            value = "select channel.* from subscribed inner join channel on subscribed.subscribed_channel_id = channel.id where subscribed.channel_id = :channel",
            nativeQuery = true
    )
    Iterable<ChannelDBDomain> findChannelSubscription(@Param("channel") Long channel);

    // Find a subscription
    // Because the subscribed is an attribute of ChannelDBDomain its necessary make a inner join
    @Query(
            value = "select channel.* from subscribed inner join channel on subscribed.subscribed_channel_id = channel.id where channel_id = :channelId and subscribed_channel_id = :subscribedChannelId",
            nativeQuery = true
    )
    Optional<ChannelDBDomain> findSubscription(@Param("channelId") Long channel,@Param("subscribedChannelId") Long subscribeChannel);

    // Delete subscriptions
    @Query(value = "delete from subscribed where channel_id = :channel", nativeQuery = true)
    void deleteSubscriptions(@Param("channel") ChannelDBDomain channel);

    // Delete subscribers
    @Query(value = "delete from subscribed where subscribed_channel_id = :subscribers_channel", nativeQuery = true)
    void deleteSubscribers(@Param("subscribers_channel") ChannelDBDomain subscribersChannel);

    // Find by email
    @Query(value = "select * from channel where email = :email", nativeQuery = true)
    ChannelDBDomain findByEmail(@Param("email") String email);

}
