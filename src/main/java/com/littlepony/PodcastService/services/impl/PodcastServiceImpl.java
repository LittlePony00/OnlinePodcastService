package com.littlepony.PodcastService.services.impl;

import com.littlepony.PodcastService.models.dtos.PodcastDTO;
import com.littlepony.PodcastService.models.entities.Podcast;
import com.littlepony.PodcastService.repositories.CategoryRepository;
import com.littlepony.PodcastService.repositories.PodcastRepository;
import com.littlepony.PodcastService.repositories.UserRepository;
import com.littlepony.PodcastService.services.PodcastService;
import com.littlepony.PodcastService.utils.exeptions.InvalidPodcastDataException;
import com.littlepony.PodcastService.utils.exeptions.PodcastNotFoundException;
import com.littlepony.PodcastService.utils.mappers.Mapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class PodcastServiceImpl implements PodcastService {
    private final PodcastRepository podcastRepository;
    private final Mapper mapper;

    public PodcastServiceImpl(PodcastRepository podcastRepository, Mapper mapper) {
        this.podcastRepository = podcastRepository;
        this.mapper = mapper;
    }

    @Override
    public PodcastDTO findById(UUID id) {
        return mapper.convertPodcastToPodcastDto(
                podcastRepository.findById(id)
                        .orElseThrow(() -> new PodcastNotFoundException("Подкаст с ID " + id + " не найден"))
        );
    }

    @Override
    public List<PodcastDTO> findAll() {
        return podcastRepository.findAll().stream()
                .map(mapper::convertPodcastToPodcastDto)
                .collect(Collectors.toList());
    }

    @Override
    public PodcastDTO create(PodcastDTO dto) {
        validatePodcastData(dto);

        Podcast podcast = mapper.convertPodcastDtoToPodcast(dto);
        if (podcast == null) {
            throw new InvalidPodcastDataException("Невозможно создать подкаст из предоставленных данных");
        }

        return mapper.convertPodcastToPodcastDto(podcastRepository.save(podcast));
    }

    @Override
    public PodcastDTO update(UUID id, PodcastDTO dto) {
        if (!podcastRepository.existsById(id)) {
            throw new PodcastNotFoundException("Подкаст с ID " + id + " не найден");
        }

        validatePodcastData(dto);

        Podcast podcast = mapper.convertPodcastDtoToPodcast(dto);
        if (podcast == null) {
            throw new InvalidPodcastDataException("Невозможно обновить подкаст из предоставленных данных");
        }

        return mapper.convertPodcastToPodcastDto(podcastRepository.save(podcast));
    }

    @Override
    public void delete(UUID id) {
        if (!podcastRepository.existsById(id)) {
            throw new PodcastNotFoundException("Подкаст с ID " + id + " не найден");
        }
        podcastRepository.deleteById(id);
    }

    @Override
    public List<PodcastDTO> findByCategory(UUID categoryId) {
        return podcastRepository.findByCategoryUuid(categoryId).stream()
                .map(mapper::convertPodcastToPodcastDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PodcastDTO> findByCreator(UUID creatorId) {
        return podcastRepository.findByCreatorUuid(creatorId).stream()
                .map(mapper::convertPodcastToPodcastDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PodcastDTO> getTopPodcasts() {
        return podcastRepository.findTop10ByOrderByRatingDesc().stream()
                .map(mapper::convertPodcastToPodcastDto)
                .collect(Collectors.toList());
    }

    private void validatePodcastData(PodcastDTO dto) {
        List<String> errors = new ArrayList<>();

        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            errors.add("Название подкаста не может быть пустым");
        }

        if (dto.getDescription() == null || dto.getDescription().trim().isEmpty()) {
            errors.add("Описание подкаста не может быть пустым");
        }

        if (dto.getCategoryName() == null || dto.getCategoryName().trim().isEmpty()) {
            errors.add("Категория подкаста должна быть указана");
        }

        if (dto.getCreatorName() == null || dto.getCreatorName().trim().isEmpty()) {
            errors.add("Создатель подкаста должен быть указан");
        }

        if (dto.getLanguage() == null || dto.getLanguage().trim().isEmpty()) {
            errors.add("Язык подкаста должен быть указан");
        }

        if (!errors.isEmpty()) {
            throw new InvalidPodcastDataException(String.join("; ", errors));
        }
    }
}
