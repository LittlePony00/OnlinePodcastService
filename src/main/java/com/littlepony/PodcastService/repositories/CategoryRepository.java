package com.littlepony.PodcastService.repositories;

import com.littlepony.PodcastService.models.entities.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends BaseRepository<Category> {
    Optional<Category> findByName(String name);
}