package com.littlepony.PodcastService.services;

import com.littlepony.PodcastService.models.UserType;
import com.littlepony.PodcastService.models.dtos.PodcastDTO;
import com.littlepony.PodcastService.models.dtos.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDTO findById(UUID id);
    List<UserDTO> findAll();
    UserDTO create(UserDTO dto);
    UserDTO update(UserDTO dto);
    void delete(UUID id);
    UserDTO findByEmail(String email);
    List<UserDTO> findByType(UserType type);
    void subscribeToPodcast(UUID userId, UUID podcastId);
    void unsubscribeFromPodcast(UUID userId, UUID podcastId);
    List<PodcastDTO> getUserSubscriptions(UUID userId);
}