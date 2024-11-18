package com.littlepony.PodcastService.utils.exeptions;

public class InvalidPodcastDataException extends RuntimeException {

    public InvalidPodcastDataException(String message) {
        super(message);
    }
}
