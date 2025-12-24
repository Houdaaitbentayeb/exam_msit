package org.example.mediaclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadVideoRequestDto {
    private String title;
    private String description;
    private String url;
    private int durationSeconds;
    private String creatorId;
    private String creatorName;
    private String creatorEmail;
}