package com.softserve.academy.Tips4Trips.dto.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface Converter<ENTITY, DTO> {

    DTO convertToDTO(final ENTITY entity);

    ENTITY convertFromDTO(final DTO dto);


    default List<ENTITY> convertFromDTO(final List<DTO> dtos) {
        List<ENTITY> entities = new ArrayList<>();
        if (dtos != null) {
            entities = dtos.stream().map(this::convertFromDTO).collect(Collectors.toList());
        }
        return entities;
    }

    default List<DTO> convertToDTO(final List<ENTITY> entities) {
        List<DTO> dtos = new ArrayList<>();
        if (entities != null) {
            dtos = entities.stream().map(this::convertToDTO).collect(Collectors.toList());
        }
        return dtos;
    }
}