package org.example.mediaclient.dto;

import lombok.*;


@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {
    private String id;
    private String title;
    private String description;
    private String url;
    private int durationSeconds;
    private CreatorDto creator;
}
