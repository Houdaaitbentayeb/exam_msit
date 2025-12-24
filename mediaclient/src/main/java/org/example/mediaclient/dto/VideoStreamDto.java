package org.example.mediaclient.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VideoStreamDto {
    private List<VideoDto> videos;
}
