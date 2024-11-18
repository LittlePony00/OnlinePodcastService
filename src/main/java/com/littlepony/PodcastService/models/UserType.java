package com.littlepony.PodcastService.models;

public enum UserType {
    LISTENER("Слушатель"),
    CREATOR("Создатель");

    private final String displayName;

    UserType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}