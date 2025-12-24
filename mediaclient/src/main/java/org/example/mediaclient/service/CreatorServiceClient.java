package org.example.mediaclient.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.lab.Creator;
import org.example.lab.CreatorServiceGrpc;
import org.example.lab.CreatorIdRequest;
import org.example.lab.Video;
import org.example.lab.VideoStream;
import org.example.mediaclient.dto.CreatorDto;
import org.example.mediaclient.dto.VideoDto;
import org.example.mediaclient.dto.VideoStreamDto;
import org.example.mediaclient.mapper.CreatorMapper;
import org.example.mediaclient.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class CreatorServiceClient {

    @GrpcClient("mediaserver")
    CreatorServiceGrpc.CreatorServiceBlockingStub creatorStub;

    @Autowired
    private CreatorMapper creatorMapper;

    @Autowired
    private VideoMapper videoMapper;

    public CreatorDto getCreator(String id) {
        CreatorIdRequest request = CreatorIdRequest.newBuilder()
                .setId(id)
                .build();
        Creator creator = creatorStub.getCreator(request);
        return creatorMapper.fromCreatorProtoToCreatorDto(creator);
    }

    public VideoStreamDto getCreatorVideos(String id) {
        CreatorIdRequest request = CreatorIdRequest.newBuilder()
                .setId(id)
                .build();
        VideoStream videoStream = creatorStub.getCreatorVideos(request);

        VideoStreamDto dto = new VideoStreamDto();
        dto.setVideos(videoStream.getVideosList().stream()
                .map(videoMapper::fromVideoProtoTovideoDto)
                .collect(Collectors.toList()));
        return dto;
    }
}
