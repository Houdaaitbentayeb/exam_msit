package org.example.mediaserver.mapper;

import org.example.lab.Video;
import org.example.mediaserver.dao.entities.VideoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;



@Component
public class VideoMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public Video fromEntityToProto(VideoEntity entity) {
        return Video.newBuilder()
                .setId(entity.getId())
                .setTitle(entity.getTitle())
                .setDescription(entity.getDescription())
                .setUrl(entity.getUrl())
                .setDurationSeconds(entity.getDurationSeconds())
                .setCreator(new CreatorMapper().fromEntityToProto(entity.getCreator()))
                .build();
    }

    public VideoEntity fromProtoToEntity(Video video) {
        VideoEntity entity = new VideoEntity();
        entity.setId(video.getId());
        entity.setTitle(video.getTitle());
        entity.setDescription(video.getDescription());
        entity.setUrl(video.getUrl());
        entity.setDurationSeconds(video.getDurationSeconds());
        entity.setCreator(new CreatorMapper().fromProtoToEntity(video.getCreator()));
        return entity;
    }
}
