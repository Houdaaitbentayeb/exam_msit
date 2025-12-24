package org.example.mediaclient.controller;

import org.example.mediaclient.dto.VideoStreamDto;
import org.example.mediaclient.dto.CreatorDto;
import org.example.mediaclient.service.CreatorServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/creators")
public class CreatorController {

    @Autowired
    private CreatorServiceClient creatorService;

    @GetMapping("/{id}")
    public ResponseEntity<CreatorDto> getCreator(@PathVariable String id) {
        CreatorDto creatorDto = creatorService.getCreator(id);
        if (creatorDto == null || creatorDto.getId() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(creatorDto);
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<VideoStreamDto> getCreatorVideos(@PathVariable String id) {
        VideoStreamDto videoStreamDto = creatorService.getCreatorVideos(id);
        return ResponseEntity.ok(videoStreamDto);
    }
}