package org.example.mediaserver.dao;

import org.example.mediaserver.entities.VideoEntity;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class VideoDao {

    private final Map<String, VideoEntity> videos = new ConcurrentHashMap<>();

    public VideoEntity save(VideoEntity video) {
        if (video.getId() == null || video.getId().isEmpty()) {
            video.setId(UUID.randomUUID().toString());
        }
        videos.put(video.getId(), video);
        return video;
    }

    public VideoEntity findById(String id) {
        return videos.get(id);
    }

    public List<VideoEntity> findByCreatorId(String creatorId) {
        return videos.values().stream()
                .filter(video -> video.getCreator() != null &&
                        video.getCreator().getId() != null &&
                        video.getCreator().getId().equals(creatorId))
                .collect(Collectors.toList());
    }

    public List<VideoEntity> findAll() {
        return new ArrayList<>(videos.values());
    }
}