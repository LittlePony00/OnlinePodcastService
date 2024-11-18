package com.littlepony.PodcastService.services.impl;

import com.littlepony.PodcastService.models.dtos.EpisodeDTO;
import com.littlepony.PodcastService.models.entities.Episode;
import com.littlepony.PodcastService.repositories.EpisodeRepository;
import com.littlepony.PodcastService.services.EpisodeService;
import com.littlepony.PodcastService.utils.mappers.Mapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class EpisodeServiceImpl implements EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final Mapper mapper;

    public EpisodeServiceImpl(EpisodeRepository episodeRepository, Mapper mapper) {
        this.episodeRepository = episodeRepository;
        this.mapper = mapper;
    }

    @Override
    public EpisodeDTO findById(UUID id) {
        return mapper.convertEpisodeToEpisodeDto(
                episodeRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Episode not found"))
        );
    }

    @Override
    public List<EpisodeDTO> findAll() {
        return episodeRepository.findAll().stream()
                .map(mapper::convertEpisodeToEpisodeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EpisodeDTO create(EpisodeDTO dto) {
        Episode episode = mapper.convertEpisodeDtoToEpisode(dto);
        return mapper.convertEpisodeToEpisodeDto(episodeRepository.save(episode));
    }

    @Override
    public EpisodeDTO update(EpisodeDTO dto) {
        if (!episodeRepository.existsById(dto.getId())) {
            throw new EntityNotFoundException("Episode not found");
        }
        Episode episode = mapper.convertEpisodeDtoToEpisode(dto);
        return mapper.convertEpisodeToEpisodeDto(episodeRepository.save(episode));
    }

    @Override
    public void delete(UUID id) {
        episodeRepository.deleteById(id);
    }

    @Override
    public List<EpisodeDTO> findByPodcast(UUID podcastId) {
        return episodeRepository.findByPodcastUuid(podcastId).stream()
                .map(mapper::convertEpisodeToEpisodeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EpisodeDTO> findRecentEpisodes(LocalDateTime after) {
        return episodeRepository.findByPublicationDateAfter(after).stream()
                .map(mapper::convertEpisodeToEpisodeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EpisodeDTO> getTopEpisodes() {
        return episodeRepository.findTop10ByOrderByListenCountDesc().stream()
                .map(mapper::convertEpisodeToEpisodeDto)
                .collect(Collectors.toList());
    }
}
