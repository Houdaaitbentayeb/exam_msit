package org.example.mediaclient.mapper;

import org.example.lab.Creator;
import org.example.mediaclient.dto.CreatorDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CreatorMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public CreatorDto fromCreatorProtoToCreatorDto(Creator creator) {
        CreatorDto dto = new CreatorDto();
        dto.setId(creator.getId());
        dto.setName(creator.getName());
        dto.setEmail(creator.getEmail());
        return dto;
    }
}