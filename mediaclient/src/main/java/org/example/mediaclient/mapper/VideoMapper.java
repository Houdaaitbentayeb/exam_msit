package org.example.mediaclient.mapper;

import org.example.lab.UploadVideoRequest;
import org.example.lab.Video;
import org.example.lab.Creator;
import org.example.mediaclient.dto.VideoStreamDto;
import org.example.mediaclient.dto.VideoDto;
import org.example.mediaclient.dto.CreatorDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper {
    private ModelMapper modelMapper = new ModelMapper();
    private CreatorMapper creatorMapper = new CreatorMapper();

    public VideoDto fromVideoProtoTovideoDto(Video video) {
        VideoDto dto = new VideoDto();
        dto.setId(video.getId());
        dto.setTitle(video.getTitle());
        dto.setDescription(video.getDescription());
        dto.setUrl(video.getUrl());
        dto.setDurationSeconds(video.getDurationSeconds());
        dto.setCreator(creatorMapper.fromCreatorProtoToCreatorDto(video.getCreator()));
        return dto;
    }
}
