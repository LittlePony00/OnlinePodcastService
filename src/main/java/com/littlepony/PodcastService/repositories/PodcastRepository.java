package com.littlepony.PodcastService.repositories;

import com.littlepony.PodcastService.models.entities.Podcast;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PodcastRepository extends BaseRepository<Podcast> {
    List<Podcast> findByCategoryUuid(UUID categoryId);
    List<Podcast> findByCreatorUuid(UUID creatorId);
    List<Podcast> findTop10ByOrderByRatingDesc();
}