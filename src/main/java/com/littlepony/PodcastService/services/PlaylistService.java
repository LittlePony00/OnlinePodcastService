package com.littlepony.PodcastService.services;

import com.littlepony.PodcastService.models.dtos.PlaylistDTO;

import java.util.List;
import java.util.UUID;

public interface PlaylistService {
    PlaylistDTO findById(UUID id);
    List<PlaylistDTO> findAll();
    PlaylistDTO create(PlaylistDTO dto);
    PlaylistDTO update(PlaylistDTO dto);
    void delete(UUID id);
    List<PlaylistDTO> findByUser(UUID userId);
    void addEpisodeToPlaylist(UUID playlistId, UUID episodeId);
    void removeEpisodeFromPlaylist(UUID playlistId, UUID episodeId);
}
