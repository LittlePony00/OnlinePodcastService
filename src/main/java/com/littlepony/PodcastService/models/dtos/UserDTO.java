package com.littlepony.PodcastService.models.dtos;

import com.littlepony.PodcastService.models.UserType;

import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private UserType type;
    private String avatarUrl;
    private String biography;

    public UserDTO() {
    }

    public UserDTO(UUID id, String name, String email, UserType type,
                   String avatarUrl, String biography, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.biography = biography;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
