package com.lucas.gowatch.gateway.mariadb.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "rating")
public class RatingDBDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "video_id", referencedColumnName = "id", nullable = false, updatable = false)
    private VideoDBDomain video;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "channel_id", referencedColumnName = "id", nullable = false, updatable = false)
    private ChannelDBDomain channel;

    @Column(nullable = false)
    private String type;

    // @type
    // Argument to be only "like" or "dislike"
    public RatingDBDomain(VideoDBDomain video, ChannelDBDomain channel, String type){
        setType(type);
        this.video = video;
        this.channel = channel;
    }

    // @type
    // Argument to be only "like" or "dislike"
    public RatingDBDomain(Long video, Long channel, String type){
        setType(type);
        this.video = new VideoDBDomain();
        this.video.setId(video);
        this.channel = new ChannelDBDomain();
        this.channel.setId(channel);
    }

    // @type
    // Argument to be only "like" or "dislike"
    public void setType(String type){
        if(type.equals("like") || type.equals("dislike"))
            this.type = type;
        else
            throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return "RatingDBDomain{" +
                "video=" + video.getId() +
                ", channel=" + channel.getId() +
                ", type='" + type + '\'' +
                '}';
    }
}
