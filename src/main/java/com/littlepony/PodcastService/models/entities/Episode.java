package com.littlepony.PodcastService.models.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "episodes")
public class Episode extends BaseEntity {
    private Podcast podcast;
    private String title;
    private String description;
    private String audioUrl;
    private Integer duration;
    private LocalDateTime publicationDate;
    private Integer listenCount;
    private Set<Playlist> playlists;

    protected Episode() {
    }

    public Episode(Podcast podcast, String title, String description, String audioUrl,
                   Integer duration, LocalDateTime publicationDate, Integer listenCount) {
        this.podcast = podcast;
        this.title = title;
        this.description = description;
        this.audioUrl = audioUrl;
        this.duration = duration;
        this.publicationDate = publicationDate;
        this.listenCount = listenCount;
    }

    @ManyToOne
    @JoinColumn(name = "podcast_id")
    public Podcast getPodcast() {
        return podcast;
    }

    public void setPodcast(Podcast podcast) {
        this.podcast = podcast;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "audio_url")
    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    @Column(name = "duration")
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Column(name = "publication_date")
    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Column(name = "listen_count")
    public Integer getListenCount() {
        return listenCount;
    }

    public void setListenCount(Integer listenCount) {
        this.listenCount = listenCount;
    }

    @ManyToMany(mappedBy = "episodes")
    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }
}
