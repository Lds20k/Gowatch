package com.lucas.gowatch;

import com.lucas.gowatch.gateway.mariadb.model.ChannelDBDomain;
import com.lucas.gowatch.gateway.mariadb.model.RatingDBDomain;
import com.lucas.gowatch.gateway.mariadb.model.VideoDBDomain;
import com.lucas.gowatch.gateway.mariadb.repository.ChannelRepository;
import com.lucas.gowatch.gateway.mariadb.repository.RatingRepository;
import com.lucas.gowatch.gateway.mariadb.repository.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GowatchPersistTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private RatingRepository ratingRepository;

    private ChannelDBDomain channel;
    private VideoDBDomain video;

    private void testFields(Map<String, List<String>> field, Object expected){
        for(int i = 0; i < field.get("field").size(); i++){
            assertThat(expected).hasFieldOrPropertyWithValue(field.get("field").get(i), field.get("value").get(i));
        }
    }

    @BeforeEach
    public void init(){
        this.channel = new ChannelDBDomain("Test", "test@email.com", "123", "Nothing to say", "São Paulo/Brasil");
        this.video = new VideoDBDomain("Test", "Description", "video.mp4", channel);
        channelRepository.save(this.channel);
        videoRepository.save(this.video);
    }

    @Test
    void should_store_a_channel(){
        Map<String, List<String>> field = new HashMap<>();
        field.put("field", List.of("username", "email", "password", "about", "location"));
        field.put("value", List.of("Lucas", "lucas@email.com", "123", "Nothing to say", "São Paulo/Brasil"));

        ChannelDBDomain anotherChannel = new ChannelDBDomain(
                field.get("value").get(0),
                field.get("value").get(1),
                field.get("value").get(2),
                field.get("value").get(3),
                field.get("value").get(4)
        );
        channelRepository.save(anotherChannel);

        Optional<ChannelDBDomain> found = channelRepository.findById(anotherChannel.getId());

        testFields(field, found.get());
    }

    @Test
    void should_store_a_video(){
        Map<String, List<String>> field = new HashMap<>();
        field.put("field", List.of("title", "description", "videoFile"));
        field.put("value", List.of("Test", "Description", "video.mp4"));

        VideoDBDomain anotherVideo = new VideoDBDomain(
                field.get("value").get(0),
                field.get("value").get(1),
                field.get("value").get(2),
                this.channel
        );
        videoRepository.save(anotherVideo);

        Optional<VideoDBDomain> found = videoRepository.findById(anotherVideo.getId());

        testFields(field, found.get());
    }

    @Test
    void should_store_a_rating(){
        Map<String, List<String>> field = new HashMap<>();
        field.put("field", List.of("type"));
        field.put("value", List.of("like"));

        RatingDBDomain rating = new RatingDBDomain(this.video, this.channel, field.get("value").get(0));
        ratingRepository.save(rating);

        Optional<RatingDBDomain> found = ratingRepository.findById(rating.getId());

        testFields(field, found.get());
    }

    @Test
    void should_store_a_subscribed(){
        ChannelDBDomain anotherChannel1 = new ChannelDBDomain("Test1", "test1@email.com", "123", "Nothing to say", "São Paulo/Brasil");
        ChannelDBDomain anotherChannel2 = new ChannelDBDomain("Test2", "test2@email.com", "123", "Nothing to say", "São Paulo/Brasil");
        channelRepository.save(anotherChannel1);
        channelRepository.save(anotherChannel2);

        channelRepository.insertSubscribed(this.channel, anotherChannel1);
        channelRepository.insertSubscribed(this.channel, anotherChannel2);

        Set<ChannelDBDomain> found = channelRepository.findSubscribedChannels(this.channel);

        Set<ChannelDBDomain> expected = new HashSet<>();
        expected.add(anotherChannel1);
        expected.add(anotherChannel2);
        assertThat(found).isEqualTo(expected);
    }

}
