package com.littlepony.PodcastService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<EntityType> extends JpaRepository<EntityType, UUID> {

}
