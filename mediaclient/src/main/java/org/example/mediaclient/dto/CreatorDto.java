package org.example.mediaclient.dto;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreatorDto {
    private String id;
    private String name;
    private String email;
}
