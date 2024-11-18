package com.littlepony.PodcastService.utils.mappers;

import com.littlepony.PodcastService.models.dtos.*;
import com.littlepony.PodcastService.models.entities.*;
import com.littlepony.PodcastService.repositories.CategoryRepository;
import com.littlepony.PodcastService.repositories.PodcastRepository;
import com.littlepony.PodcastService.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class Mapper {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PodcastRepository podcastRepository;

    public Mapper(ModelMapper modelMapper,
                  UserRepository userRepository,
                  CategoryRepository categoryRepository,
                  PodcastRepository podcastRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.podcastRepository = podcastRepository;
    }

    public UserDTO convertUserToUserDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertUserDtoToUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        if (user.getPlaylists() == null) {
            user.setPlaylists(new ArrayList<>());
        }
        if (user.getSubscribedPodcasts() == null) {
            user.setSubscribedPodcasts(new HashSet<>());
        }
        return user;
    }

    public PodcastDTO convertPodcastToPodcastDto(Podcast podcast) {
        PodcastDTO podcastDTO = modelMapper.map(podcast, PodcastDTO.class);
        podcastDTO.setCategoryName(podcast.getCategory().getName());
        podcastDTO.setCreatorName(podcast.getCreator().getName());
        return podcastDTO;
    }

    public Podcast convertPodcastDtoToPodcast(PodcastDTO podcastDTO) {
        Podcast podcast = modelMapper.map(podcastDTO, Podcast.class);
        podcast.setCategory(categoryRepository.findByName(podcastDTO.getCategoryName()).orElse(null));
        podcast.setCreator(userRepository.findByName(podcastDTO.getCreatorName()).orElse(null));

        if (podcast.getCategory() == null || podcast.getCreator() == null) {
            return null;
        }

        if (podcast.getEpisodes() == null) {
            podcast.setEpisodes(new ArrayList<>());
        }
        if (podcast.getSubscribers() == null) {
            podcast.setSubscribers(new HashSet<>());
        }
        return podcast;
    }

    public EpisodeDTO convertEpisodeToEpisodeDto(Episode episode) {
        return modelMapper.map(episode, EpisodeDTO.class);
    }

    public Episode convertEpisodeDtoToEpisode(EpisodeDTO episodeDTO) {
        Episode episode = modelMapper.map(episodeDTO, Episode.class);
        if (episode.getPlaylists() == null) {
            episode.setPlaylists(new HashSet<>());
        }
        return episode;
    }

    public CategoryDTO convertCategoryToCategoryDto(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public Category convertCategoryDtoToCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        if (category.getPodcasts() == null) {
            category.setPodcasts(new ArrayList<>());
        }
        return category;
    }

    public PlaylistDTO convertPlaylistToPlaylistDto(Playlist playlist) {
        PlaylistDTO playlistDTO = modelMapper.map(playlist, PlaylistDTO.class);
        playlistDTO.setUserId(playlist.getUser().getUuid());
        playlistDTO.setEpisodes(playlist.getEpisodes().stream()
                .map(this::convertEpisodeToEpisodeDto)
                .collect(Collectors.toList()));
        return playlistDTO;
    }

    public Playlist convertPlaylistDtoToPlaylist(PlaylistDTO playlistDTO) {
        Playlist playlist = modelMapper.map(playlistDTO, Playlist.class);
        playlist.setUser(userRepository.findById(playlistDTO.getUserId()).orElse(null));

        if (playlist.getUser() == null) {
            return null;
        }

        if (playlist.getCreationDate() == null) {
            playlist.setCreationDate(LocalDateTime.now());
        }

        if (playlist.getEpisodes() == null) {
            playlist.setEpisodes(new HashSet<>());
        }
        return playlist;
    }
}
