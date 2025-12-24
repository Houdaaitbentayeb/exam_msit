package org.example.mediaclient.service;


import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.lab.UploadVideoRequest;
import org.example.lab.Video;
import org.example.lab.VideoIdRequest;
import org.example.lab.VideoServiceGrpc;
import org.example.mediaclient.dto.VideoDto;
import org.example.mediaclient.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceClient {

    @GrpcClient("mediaserver")
    VideoServiceGrpc.VideoServiceBlockingStub videoStub;

    @Autowired
    private VideoMapper videoMapper;

    public VideoDto uploadVideo(UploadVideoRequest videoRequest) {
        Video video = videoStub.uploadVideo(videoRequest);
        return videoMapper.fromVideoProtoTovideoDto(video);
    }

    public VideoDto getVideo(String id) {
        VideoIdRequest request = VideoIdRequest.newBuilder()
                .setId(id)
                .build();
        Video video = videoStub.getVideo(request);
        return videoMapper.fromVideoProtoTovideoDto(video);
    }
}