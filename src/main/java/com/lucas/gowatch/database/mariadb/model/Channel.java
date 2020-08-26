package com.lucas.gowatch.database.mariadb.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "int unsigned default 0")
    private int subscribersCounter;

    private String about;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDate creationDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "channel", fetch = FetchType.LAZY)
    private Set<Video> videos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "channel", fetch = FetchType.LAZY)
    private Set<Rating> ratingVideos;

    public Channel(String username, String email, String password, String about,String location){
        this.username = username;
        this.email = email;
        this.password = password;
        this.about = about;
        this.location = location;
        this.creationDate = LocalDate.now();
    }

    public Set<Video> getVideos(){
        return videos;
    }

    public void setVideos(Set<Video> videos){
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", subscribersCounter=" + subscribersCounter +
                ", about='" + about + '\'' +
                ", location='" + location + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
