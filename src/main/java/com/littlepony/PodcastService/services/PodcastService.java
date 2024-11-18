package com.littlepony.PodcastService.services;

import com.littlepony.PodcastService.models.dtos.PodcastDTO;
import com.littlepony.PodcastService.utils.exeptions.InvalidPodcastDataException;
import com.littlepony.PodcastService.utils.exeptions.PodcastNotFoundException;

import java.util.List;
import java.util.UUID;

public interface PodcastService {
    PodcastDTO findById(UUID id) throws PodcastNotFoundException;
    List<PodcastDTO> findAll();
    PodcastDTO create(PodcastDTO dto) throws InvalidPodcastDataException;
    PodcastDTO update(UUID id, PodcastDTO dto) throws PodcastNotFoundException, InvalidPodcastDataException;
    void delete(UUID id) throws PodcastNotFoundException;
    List<PodcastDTO> findByCategory(UUID categoryId);
    List<PodcastDTO> findByCreator(UUID creatorId);
    List<PodcastDTO> getTopPodcasts();
}