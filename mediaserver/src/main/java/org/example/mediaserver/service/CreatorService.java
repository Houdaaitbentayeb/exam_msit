package org.example.mediaserver.service;


import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.lab.*;
import org.example.mediaserver.dao.CreatorDao;
import org.example.mediaserver.dao.VideoDao;
import org.example.mediaserver.entities.CreatorEntity;
import org.example.mediaserver.entities.VideoEntity;
import org.example.mediaserver.mapper.CreatorMapper;
import org.example.mediaserver.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class CreatorService extends CreatorServiceGrpc.CreatorServiceImplBase {

    @Autowired
    private CreatorDao creatorDao;

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private CreatorMapper creatorMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public void getCreator(CreatorIdRequest request, StreamObserver<Creator> responseObserver) {
        CreatorEntity creatorEntity = creatorDao.findById(request.getId());
        if (creatorEntity != null) {
            Creator creator = creatorMapper.fromEntityToProto(creatorEntity);
            responseObserver.onNext(creator);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getCreatorVideos(CreatorIdRequest request, StreamObserver<VideoStream> responseObserver) {
        List<VideoEntity> videos = videoDao.findByCreatorId(request.getId());
        List<Video> videoProtos = videos.stream()
                .map(videoMapper::fromEntityToProto)
                .collect(Collectors.toList());

        VideoStream videoStream = VideoStream.newBuilder()
                .addAllVideos(videoProtos)
                .build();

        responseObserver.onNext(videoStream);
        responseObserver.onCompleted();
    }
}