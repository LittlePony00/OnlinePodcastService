package com.littlepony.PodcastService.models.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class EpisodeDTO {
    private UUID id;
    private String title;
    private String description;
    private String audioUrl;
    private Integer duration;
    private LocalDateTime publicationDate;
    private Integer listenCount;

    public EpisodeDTO() {
    }

    public EpisodeDTO(UUID id, String title, String description, String audioUrl,
                     Integer duration, LocalDateTime publicationDate, Integer listenCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.audioUrl = audioUrl;
        this.duration = duration;
        this.publicationDate = publicationDate;
        this.listenCount = listenCount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getListenCount() {
        return listenCount;
    }

    public void setListenCount(Integer listenCount) {
        this.listenCount = listenCount;
    }
}
