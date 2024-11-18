package com.littlepony.PodcastService.repositories;

import com.littlepony.PodcastService.models.entities.Playlist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlaylistRepository extends BaseRepository<Playlist> {
    List<Playlist> findByUserUuid(UUID userId);
}