package org.example.mediaclient.controller;

import org.example.lab.Creator;
import org.example.lab.UploadVideoRequest;
import org.example.mediaclient.dto.UploadVideoRequestDto;
import org.example.mediaclient.dto.VideoDto;
import org.example.mediaclient.service.VideoServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoServiceClient videoService;

    @PostMapping("/upload")
    public ResponseEntity<VideoDto> uploadVideo() {
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
        return ResponseEntity.ok(videoDto);
    }

    @PostMapping("/upload-custom")
    public ResponseEntity<VideoDto> uploadCustomVideo(@RequestBody UploadVideoRequestDto requestDto) {
        Creator creator = Creator.newBuilder()
                .setName(requestDto.getCreatorName())
                .setEmail(requestDto.getCreatorEmail())
                .setId(requestDto.getCreatorId())
                .build();

        UploadVideoRequest request = UploadVideoRequest.newBuilder()
                .setTitle(requestDto.getTitle())
                .setDescription(requestDto.getDescription())
                .setUrl(requestDto.getUrl())
                .setDurationSeconds(requestDto.getDurationSeconds())
                .setCreator(creator)
                .build();

        VideoDto videoDto = videoService.uploadVideo(request);
        return ResponseEntity.ok(videoDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> getVideo(@PathVariable String id) {
        VideoDto videoDto = videoService.getVideo(id);
        if (videoDto == null || videoDto.getId() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(videoDto);
    }
}