package com.littlepony.PodcastService.models.entities;//package com.littlepony.PodcastService.models.entities;
//
//import jakarta.persistence.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "favorite_episodes")
//public class FavoriteEpisode extends BaseEntity {
//
//    private User user;
//    private List<Episode> episodes = new ArrayList<>();
//
//    protected FavoriteEpisode() {}
//
//    public FavoriteEpisode(User user) {
//        this.user = user;
//    }
//
//    public FavoriteEpisode(User user, List<Episode> episodes) {
//        this.user = user;
//        this.episodes = !episodes.isEmpty() ? episodes : this.episodes;
//    }
//
//    @OneToOne
//    @JoinColumn(name = "user_id", nullable = false, unique = true)
//    public User getUser() {
//        return this.user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "favorite_episode",
//            joinColumns = @JoinColumn(name = "favorite_episode_id"),
//            inverseJoinColumns = @JoinColumn(name = "episode_id")
//    )
//    public List<Episode> getEpisodes() {
//        return episodes;
//    }
//
//    public void setEpisodes(List<Episode> tracks) {
//        this.episodes = tracks;
//    }
//}
