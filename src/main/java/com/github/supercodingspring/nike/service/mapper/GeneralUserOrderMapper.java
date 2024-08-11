package com.github.supercodingspring.nike.service.mapper;

import com.github.supercodingspring.nike.repository.Orders;
import com.github.supercodingspring.nike.web.dto.GeneralUserOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GeneralUserOrderMapper {
    GeneralUserOrderMapper INSTANCE = Mappers.getMapper(GeneralUserOrderMapper.class);

    @Mapping(target = "modelId", source = "sneaker.modelId")
    GeneralUserOrder OrderToGeneralUserOrder (Orders orders);
}
