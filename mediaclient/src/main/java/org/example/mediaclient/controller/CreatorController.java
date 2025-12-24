package org.example.mediaclient.controller;

import org.example.mediaclient.dto.VideoStreamDto;
import org.example.mediaclient.dto.CreatorDto;
import org.example.mediaclient.service.CreatorServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/creators")

public class CreatorController {

    @Autowired
    private CreatorServiceClient creatorService;

    @GetMapping("/{id}")
    public CreatorDto getCreator(@PathVariable String id) {
        return creatorService.getCreator(id);
    }

    @GetMapping("/{id}/videos")
    public VideoStreamDto getCreatorVideos(@PathVariable String id) {
        return creatorService.getCreatorVideos(id);
    }
}