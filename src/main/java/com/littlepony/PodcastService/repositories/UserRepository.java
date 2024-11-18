package com.littlepony.PodcastService.repositories;

import com.littlepony.PodcastService.models.UserType;
import com.littlepony.PodcastService.models.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
    List<User> findByType(UserType type);
}
