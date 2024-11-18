package com.littlepony.PodcastService.services.impl;

import com.littlepony.PodcastService.models.UserType;
import com.littlepony.PodcastService.models.dtos.PodcastDTO;
import com.littlepony.PodcastService.models.dtos.UserDTO;
import com.littlepony.PodcastService.models.entities.Podcast;
import com.littlepony.PodcastService.models.entities.User;
import com.littlepony.PodcastService.repositories.PodcastRepository;
import com.littlepony.PodcastService.repositories.UserRepository;
import com.littlepony.PodcastService.services.UserService;
import com.littlepony.PodcastService.utils.mappers.Mapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PodcastRepository podcastRepository;

    private final Mapper mapper;

    public UserServiceImpl(UserRepository userRepository, PodcastRepository podcastRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.podcastRepository = podcastRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDTO findById(UUID id) {
        return mapper.convertUserToUserDto(
                userRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("User not found"))
        );
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(mapper::convertUserToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO create(UserDTO dto) {
        User user = mapper.convertUserDtoToUser(dto);
        return mapper.convertUserToUserDto(userRepository.save(user));
    }

    @Override
    public UserDTO update(UserDTO dto) {
        if (!userRepository.existsById(dto.getId())) {
            throw new EntityNotFoundException("User not found");
        }
        User user = mapper.convertUserDtoToUser(dto);
        return mapper.convertUserToUserDto(userRepository.save(user));
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO findByEmail(String email) {
        return mapper.convertUserToUserDto(
                userRepository.findByEmail(email)
                        .orElseThrow(() -> new EntityNotFoundException("User not found"))
        );
    }

    @Override
    public List<UserDTO> findByType(UserType type) {
        return userRepository.findByType(type).stream()
                .map(mapper::convertUserToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void subscribeToPodcast(UUID userId, UUID podcastId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Podcast podcast = podcastRepository.findById(podcastId)
                .orElseThrow(() -> new EntityNotFoundException("Podcast not found"));

        if (user.getSubscribedPodcasts().contains(podcast)) {
            throw new IllegalStateException("User is already subscribed to this podcast");
        }

        user.getSubscribedPodcasts().add(podcast);
        userRepository.save(user);
    }

    @Override
    public void unsubscribeFromPodcast(UUID userId, UUID podcastId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Podcast podcast = podcastRepository.findById(podcastId)
                .orElseThrow(() -> new EntityNotFoundException("Podcast not found"));

        if (!user.getSubscribedPodcasts().contains(podcast)) {
            throw new IllegalStateException("User is not subscribed to this podcast");
        }

        user.getSubscribedPodcasts().remove(podcast);
        userRepository.save(user);
    }

    @Override
    public List<PodcastDTO> getUserSubscriptions(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return user.getSubscribedPodcasts().stream()
                .map(mapper::convertPodcastToPodcastDto)
                .collect(Collectors.toList());
    }
}