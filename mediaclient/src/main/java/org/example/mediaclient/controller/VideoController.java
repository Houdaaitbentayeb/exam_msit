package org.example.mediaclient.controller;

import org.example.lab.Creator;
import org.example.lab.UploadVideoRequest;
import org.example.mediaclient.dto.VideoDto;
import org.example.mediaclient.service.VideoServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoServiceClient videoService;

    @PostMapping("/upload")
    public VideoDto uploadVideo() {
        Creator creator = Creator.newBuilder()
                .setName("Xproce")
                .setEmail("hirchoua.badr@gmail.com")
                .setId("2")
                .build();

        UploadVideoRequest request = UploadVideoRequest.newBuilder()
                .setTitle("grpc 101")
                .setDescription("The gRPC 101 is an introductory course to master Grpc")
                .setUrl("https://github.com/badrhr/gRPC101")
                .setDurationSeconds(380)
                .setCreator(creator)
                .build();

        VideoDto videoDto = videoService.uploadVideo(request);
        System.out.println(videoDto);
        return videoDto;
    }

    @GetMapping("/{id}")
    public VideoDto getVideo(@PathVariable String id) {
        return videoService.getVideo(id);
    }
}