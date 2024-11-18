package com.littlepony.PodcastService.models.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "playlists")
public class Playlist extends BaseEntity {
    private User user;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    private Set<Episode> episodes;

    protected Playlist() {
    }

    public Playlist(User user, String name, String description, boolean isPublic) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.creationDate = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "creation_date")
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @ManyToMany
    @JoinTable(
            name = "playlist_episode",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "episode_id")
    )
    public Set<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Set<Episode> episodes) {
        this.episodes = episodes;
    }
}
