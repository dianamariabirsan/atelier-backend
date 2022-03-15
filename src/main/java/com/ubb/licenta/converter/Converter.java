package com.ubb.licenta.converter;


import com.ubb.licenta.dto.BaseDto;
import com.ubb.licenta.model.BaseEntity;

public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}
