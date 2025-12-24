package org.example.mediaserver.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.lab.UploadVideoRequest;
import org.example.lab.Video;
import org.example.lab.VideoIdRequest;
import org.example.lab.VideoServiceGrpc;
import org.example.mediaserver.dao.CreatorDao;
import org.example.mediaserver.dao.VideoDao;
import org.example.mediaserver.entities.CreatorEntity;
import org.example.mediaserver.entities.VideoEntity;
import org.example.mediaserver.mapper.CreatorMapper;
import org.example.mediaserver.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;


@GrpcService
public class VideoService extends VideoServiceGrpc.VideoServiceImplBase {

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private CreatorDao creatorDao;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private CreatorMapper creatorMapper;

    @Override
    public void uploadVideo(UploadVideoRequest request, StreamObserver<Video> responseObserver) {
        CreatorEntity creatorEntity = creatorMapper.fromProtoToEntity(request.getCreator());
        creatorDao.save(creatorEntity);

        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setTitle(request.getTitle());
        videoEntity.setDescription(request.getDescription());
        videoEntity.setUrl(request.getUrl());
        videoEntity.setDurationSeconds(request.getDurationSeconds());
        videoEntity.setCreator(creatorEntity);

        VideoEntity savedVideo = videoDao.save(videoEntity);
        Video video = videoMapper.fromEntityToProto(savedVideo);

        responseObserver.onNext(video);
        responseObserver.onCompleted();
    }

    @Override
    public void getVideo(VideoIdRequest request, StreamObserver<Video> responseObserver) {
        VideoEntity videoEntity = videoDao.findById(request.getId());
        if (videoEntity != null) {
            Video video = videoMapper.fromEntityToProto(videoEntity);
            responseObserver.onNext(video);
        }
        responseObserver.onCompleted();
    }
}
