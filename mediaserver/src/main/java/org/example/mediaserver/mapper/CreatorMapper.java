package org.example.mediaserver.mapper;

import org.example.lab.Creator;
import org.example.mediaserver.entities.CreatorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Component
public class CreatorMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public Creator fromEntityToProto(CreatorEntity entity) {
        return Creator.newBuilder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setEmail(entity.getEmail())
                .build();
    }

    public CreatorEntity fromProtoToEntity(Creator creator) {
        CreatorEntity entity = new CreatorEntity();
        entity.setId(creator.getId());
        entity.setName(creator.getName());
        entity.setEmail(creator.getEmail());
        return entity;
    }
}
