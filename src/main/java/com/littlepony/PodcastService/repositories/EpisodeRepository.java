package com.littlepony.PodcastService.repositories;

import com.littlepony.PodcastService.models.entities.Category;
import com.littlepony.PodcastService.models.entities.Episode;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EpisodeRepository extends BaseRepository<Episode> {
    List<Episode> findByPodcastUuid(UUID podcastId);
    List<Episode> findByPublicationDateAfter(LocalDateTime date);
    List<Episode> findTop10ByOrderByListenCountDesc();
}