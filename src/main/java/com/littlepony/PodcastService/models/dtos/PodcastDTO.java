package com.littlepony.PodcastService.models.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class PodcastDTO {
    private UUID id;
    private String title;
    private String description;
    private String coverUrl;
    private String categoryName;
    private String creatorName;
    private LocalDateTime creationDate;
    private String language;
    private Double rating;

    public PodcastDTO() {
    }

    public PodcastDTO(UUID id, String title, String description, String coverUrl,
                      String categoryName, String creatorName, LocalDateTime creationDate,
                      String language, Double rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.coverUrl = coverUrl;
        this.categoryName = categoryName;
        this.creatorName = creatorName;
        this.creationDate = creationDate;
        this.language = language;
        this.rating = rating;
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

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
