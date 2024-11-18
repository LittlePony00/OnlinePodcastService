package com.littlepony.PodcastService.models.dtos;//package com.littlepony.PodcastService.models.dtos;
//
//import java.util.ArrayList;
//import java.util.UUID;
//import java.util.List;
//
//public class FavoriteEpisodeDTO {
//    private UUID userId;
//    private List<UUID> EpisodeIds;
//    private int trackCount;
//
//    protected FavoriteEpisodeDTO() {}
//
//    public FavoriteEpisodeDTO(UUID userId, List<UUID> EpisodeIds, int trackCount) {
//        this.userId = userId;
//        this.EpisodeIds = EpisodeIds != null ? EpisodeIds : new ArrayList<>();
//        this.trackCount = trackCount;
//    }
//
//    public UUID getUserId() {
//        return userId;
//    }
//
//    public void setUserId(UUID userId) {
//        this.userId = userId;
//    }
//
//    public List<UUID> getEpisodeIds() {
//        return EpisodeIds;
//    }
//
//    public void setEpisodeIds(List<UUID> episodeIds) {
//        this.EpisodeIds = episodeIds;
//    }
//
//    public int getTrackCount() {
//        return trackCount;
//    }
//
//    public void setTrackCount(int trackCount) {
//        this.trackCount = trackCount;
//    }
//}
