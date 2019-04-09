package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.PositionDTO;
import com.softserve.academy.Tips4Trips.entity.Position;
import org.springframework.stereotype.Component;

@Component
public class PositionConverter implements Converter<Position, PositionDTO> {

    @Override
    public PositionDTO apply(Position position) {
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setCoordinateX(position.getCoordinateX());
        positionDTO.setCoordinateY(position.getCoordinateY());
        return positionDTO;
    }
}
