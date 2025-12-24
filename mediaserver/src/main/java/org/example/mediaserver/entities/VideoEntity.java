package org.example.mediaserver.entities;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoEntity {
    private String id;
    private String title;
    private String description;
    private String url;
    private int durationSeconds;
    private CreatorEntity creator;
}