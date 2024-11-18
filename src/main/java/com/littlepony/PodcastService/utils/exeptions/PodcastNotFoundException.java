package com.littlepony.PodcastService.utils.exeptions;

public class PodcastNotFoundException extends RuntimeException {

    public PodcastNotFoundException(String message) {
        super(message);
    }
}
