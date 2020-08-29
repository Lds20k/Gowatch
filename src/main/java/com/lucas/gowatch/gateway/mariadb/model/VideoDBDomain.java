package com.lucas.gowatch.gateway.mariadb.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "video")
public class VideoDBDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "int unsigned default 0")
    private int views;

    private String description;

    @Column(nullable = false)
    private LocalDate uploadDate;

    @Column(nullable = false)
    private String videoFile;

    @Column(nullable = false, columnDefinition = "int unsigned default 0")
    private int likes;

    @Column(nullable = false, columnDefinition = "int unsigned default 0")
    private int dislikes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "channel_id", nullable = false, updatable = false)
    private ChannelDBDomain channel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "video", fetch = FetchType.LAZY)
    private Set<RatingDBDomain> channelsRating;

    public VideoDBDomain(String title, String description, String videoFile, ChannelDBDomain channel){
        this.title = title;
        this.description = description;
        this.videoFile = videoFile;
        this.channel = channel;
        this.uploadDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "VideoDBDomain{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", views=" + views +
                ", description='" + description + '\'' +
                ", uploadDate=" + uploadDate +
                ", videoFile='" + videoFile + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", channel_id=" + channel.getId() +
                '}';
    }
}
