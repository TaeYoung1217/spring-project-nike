package com.github.supercodingspring.nike.service.mapper;

import com.github.supercodingspring.nike.repository.Sneaker;
import com.github.supercodingspring.nike.repository.SneakerModelTraits;
import com.github.supercodingspring.nike.web.dto.SneakerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SneakerMapper {
    SneakerMapper INSTANCE = Mappers.getMapper(SneakerMapper.class);

    @Mapping(target = "modelId", source = "modelId")
    @Mapping(target = "descriptions", source = "sneakerModelTraits", qualifiedByName = "convert")
    SneakerDto sneakerToSneakerDto(Sneaker sneaker);

    @Named("convert")
    static String listToDescription(SneakerModelTraits sneakerModelTraits){
        return sneakerModelTraits.getSneakerTraits().getDescriptions();
    }
}
