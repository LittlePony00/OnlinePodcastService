package com.littlepony.PodcastService.models.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PlaylistDTO {
    private UUID userId;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    private List<EpisodeDTO> episodes;

    public PlaylistDTO() {
    }

    public PlaylistDTO(UUID userId, String name, String description,
                      LocalDateTime creationDate, List<EpisodeDTO> episodes) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.episodes = episodes;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<EpisodeDTO> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<EpisodeDTO> episodes) {
        this.episodes = episodes;
    }
}
