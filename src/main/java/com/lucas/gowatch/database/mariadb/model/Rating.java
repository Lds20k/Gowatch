package com.lucas.gowatch.database.mariadb.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "video_id", referencedColumnName = "id", nullable = false, updatable = false)
    private Video video;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "channel_id", referencedColumnName = "id", nullable = false, updatable = false)
    private Channel channel;

    @Column(nullable = false)
    private String type;

    // @type
    // Argument to be only "like" or "dislike"
    public Rating(Video video, Channel channel, String type){
        setType(type);
        this.video = video;
        this.channel = channel;
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
        return "Rating{" +
                "video=" + video.getId() +
                ", channel=" + channel.getId() +
                ", type='" + type + '\'' +
                '}';
    }
}
