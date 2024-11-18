package com.littlepony.PodcastService.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "podcasts")
public class Podcast extends BaseEntity {
    private String title;
    private String description;
    private String coverUrl;
    private Category category;
    private User creator;
    private LocalDateTime creationDate;
    private String language;
    private Double rating;
    private List<Episode> episodes;
    private Set<User> subscribers;

    protected Podcast() {
    }

    public Podcast(String title, String description, String coverUrl, Category category,
                   User creator, String language) {
        this.title = title;
        this.description = description;
        this.coverUrl = coverUrl;
        this.category = category;
        this.creator = creator;
        this.creationDate = LocalDateTime.now();
        this.language = language;
        this.rating = 0.0;
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

    @Column(name = "cover_url")
    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name = "creator_id")
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Column(name = "creation_date")
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Column(name = "rating")
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @OneToMany(mappedBy = "podcast")
    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    @ManyToMany(mappedBy = "subscribedPodcasts")
    public Set<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<User> subscribers) {
        this.subscribers = subscribers;
    }
}
