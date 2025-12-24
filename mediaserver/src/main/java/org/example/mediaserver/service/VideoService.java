package org.example.mediaserver.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.lab.UploadVideoRequest;
import org.example.lab.Video;
import org.example.lab.VideoIdRequest;
import org.example.lab.VideoServiceGrpc;
import org.example.mediaserver.dao.repositories.CreatorRepository;
import org.example.mediaserver.dao.repositories.VideoRepository;
import org.example.mediaserver.dao.entities.CreatorEntity;
import org.example.mediaserver.dao.entities.VideoEntity;
import org.example.mediaserver.mapper.CreatorMapper;
import org.example.mediaserver.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;


@GrpcService
public class VideoService extends VideoServiceGrpc.VideoServiceImplBase {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CreatorRepository creatorRepository;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private CreatorMapper creatorMapper;

    @Override
    public void uploadVideo(UploadVideoRequest request, StreamObserver<Video> responseObserver) {
        CreatorEntity creatorEntity = creatorMapper.fromProtoToEntity(request.getCreator());
        creatorRepository.save(creatorEntity);

        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setTitle(request.getTitle());
        videoEntity.setDescription(request.getDescription());
        videoEntity.setUrl(request.getUrl());
        videoEntity.setDurationSeconds(request.getDurationSeconds());
        videoEntity.setCreator(creatorEntity);

        VideoEntity savedVideo = videoRepository.save(videoEntity);
        Video video = videoMapper.fromEntityToProto(savedVideo);

        responseObserver.onNext(video);
        responseObserver.onCompleted();
    }

    @Override
    public void getVideo(VideoIdRequest request, StreamObserver<Video> responseObserver) {
        VideoEntity videoEntity = videoRepository.findById(request.getId());
        if (videoEntity != null) {
            Video video = videoMapper.fromEntityToProto(videoEntity);
            responseObserver.onNext(video);
        }
        responseObserver.onCompleted();
    }
}
