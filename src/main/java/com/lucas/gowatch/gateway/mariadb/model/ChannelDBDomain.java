package com.lucas.gowatch.gateway.mariadb.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "channel")
public class ChannelDBDomain implements Serializable {
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
    private LocalDate creationDate = LocalDate.now();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "channel", fetch = FetchType.LAZY)
    private Set<VideoDBDomain> videos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "channel", fetch = FetchType.LAZY)
    private Set<RatingDBDomain> ratingVideos;

    // Self-Relationship
    // ChannelDBDomain is subscribed
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "subscribed",
            joinColumns = { @JoinColumn(name = "channel_id", updatable = false, nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "subscribed_channel_id", updatable = false, nullable = false) }
    )
    private Set<ChannelDBDomain> subscribed = new HashSet<>();

    // Self-Relationship
    // ChannelDBDomain has subscribers
    @ManyToMany(mappedBy = "subscribed", fetch = FetchType.LAZY)
    private Set<ChannelDBDomain> subscribers = new HashSet<>();

    public ChannelDBDomain(String username, String email, String password, String about, String location){
        this.username = username;
        this.email = email;
        this.password = password;
        this.about = about;
        this.location = location;
    }

    @Override
    public String toString() {
        return "ChannelDBDomain{" +
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
