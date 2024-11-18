package com.littlepony.PodcastService.services;

import com.littlepony.PodcastService.models.dtos.EpisodeDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EpisodeService {
    EpisodeDTO findById(UUID id);
    List<EpisodeDTO> findAll();
    EpisodeDTO create(EpisodeDTO dto);
    EpisodeDTO update(EpisodeDTO dto);
    void delete(UUID id);
    List<EpisodeDTO> findByPodcast(UUID podcastId);
    List<EpisodeDTO> findRecentEpisodes(LocalDateTime after);
    List<EpisodeDTO> getTopEpisodes();
}
