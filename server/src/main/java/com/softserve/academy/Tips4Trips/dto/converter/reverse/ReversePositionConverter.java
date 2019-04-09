package com.softserve.academy.Tips4Trips.dto.converter.reverse;

import com.softserve.academy.Tips4Trips.dto.PositionDTO;
import com.softserve.academy.Tips4Trips.dto.converter.Converter;
import com.softserve.academy.Tips4Trips.entity.Position;
import org.springframework.stereotype.Component;

@Component
public class ReversePositionConverter implements Converter<PositionDTO, Position> {
    @Override
    public Position apply(PositionDTO positionDTO) {
        Position position = new Position();
        position.setCoordinateX(positionDTO.getCoordinateX());
        position.setCoordinateY(positionDTO.getCoordinateY());
        return position;
    }
}
