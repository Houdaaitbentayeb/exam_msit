package org.example.mediaserver.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatorEntity {
    private String id;
    private String name;
    private String email;
}