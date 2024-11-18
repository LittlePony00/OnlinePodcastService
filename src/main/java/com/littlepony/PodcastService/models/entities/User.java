package com.littlepony.PodcastService.models.entities;

import com.littlepony.PodcastService.models.UserType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String name;
    private String email;
    private String password;
    private LocalDateTime registrationDate;
    private UserType type;
    private String avatarUrl;
    private String biography;
    private List<Podcast> podcasts;
    private List<Playlist> playlists;
    private Set<Podcast> subscribedPodcasts;

    protected User() {
    }

    public User(String name, String email, String password, UserType type,
                String avatarUrl, String biography) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.registrationDate = LocalDateTime.now();
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.biography = biography;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "registration_date")
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Column(name = "avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Column(name = "biography")
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @OneToMany(mappedBy = "creator")
    public List<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    @OneToMany(mappedBy = "user")
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    @ManyToMany
    @JoinTable(
            name = "subscriptions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "podcast_id")
    )
    public Set<Podcast> getSubscribedPodcasts() {
        return subscribedPodcasts;
    }

    public void setSubscribedPodcasts(Set<Podcast> subscribedPodcasts) {
        this.subscribedPodcasts = subscribedPodcasts;
    }
}
