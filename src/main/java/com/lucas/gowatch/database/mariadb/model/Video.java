package com.lucas.gowatch.database.mariadb.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Video {
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
    private Channel channel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "video", fetch = FetchType.LAZY)
    private Set<Rating> channelsRating;

    public Video(String title, String description, String videoFile, Channel channel){
        this.title = title;
        this.description = description;
        this.uploadDate = LocalDate.now();
        this.videoFile = videoFile;
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Video{" +
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
