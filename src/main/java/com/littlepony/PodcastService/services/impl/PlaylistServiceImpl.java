package com.littlepony.PodcastService.services.impl;

import com.littlepony.PodcastService.models.dtos.PlaylistDTO;
import com.littlepony.PodcastService.models.entities.Episode;
import com.littlepony.PodcastService.models.entities.Playlist;
import com.littlepony.PodcastService.repositories.EpisodeRepository;
import com.littlepony.PodcastService.repositories.PlaylistRepository;
import com.littlepony.PodcastService.services.PlaylistService;
import com.littlepony.PodcastService.utils.mappers.Mapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final EpisodeRepository episodeRepository;
    private final Mapper mapper;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository, 
                             EpisodeRepository episodeRepository,
                             Mapper mapper) {
        this.playlistRepository = playlistRepository;
        this.episodeRepository = episodeRepository;
        this.mapper = mapper;
    }

    @Override
    public PlaylistDTO findById(UUID id) {
        return mapper.convertPlaylistToPlaylistDto(
                playlistRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Playlist not found"))
        );
    }

    @Override
    public List<PlaylistDTO> findAll() {
        return playlistRepository.findAll().stream()
                .map(mapper::convertPlaylistToPlaylistDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlaylistDTO create(PlaylistDTO dto) {
        Playlist playlist = mapper.convertPlaylistDtoToPlaylist(dto);
        return mapper.convertPlaylistToPlaylistDto(playlistRepository.save(playlist));
    }

    @Override
    public PlaylistDTO update(PlaylistDTO dto) {
        if (!playlistRepository.existsById(dto.getUserId())) {
            throw new EntityNotFoundException("Playlist not found");
        }
        Playlist playlist = mapper.convertPlaylistDtoToPlaylist(dto);
        return mapper.convertPlaylistToPlaylistDto(playlistRepository.save(playlist));
    }

    @Override
    public void delete(UUID id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public List<PlaylistDTO> findByUser(UUID userId) {
        return playlistRepository.findByUserUuid(userId).stream()
                .map(mapper::convertPlaylistToPlaylistDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addEpisodeToPlaylist(UUID playlistId, UUID episodeId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new EntityNotFoundException("Playlist not found"));
        
        Episode episode = episodeRepository.findById(episodeId)
                .orElseThrow(() -> new EntityNotFoundException("Episode not found"));
        
        playlist.getEpisodes().add(episode);
        playlistRepository.save(playlist);
    }

    @Override
    public void removeEpisodeFromPlaylist(UUID playlistId, UUID episodeId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new EntityNotFoundException("Playlist not found"));
        
        Episode episode = episodeRepository.findById(episodeId)
                .orElseThrow(() -> new EntityNotFoundException("Episode not found"));
        
        playlist.getEpisodes().remove(episode);
        playlistRepository.save(playlist);
    }
}
