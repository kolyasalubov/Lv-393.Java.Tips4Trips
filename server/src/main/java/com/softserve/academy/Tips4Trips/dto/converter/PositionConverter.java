package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.PositionDTO;
import com.softserve.academy.Tips4Trips.entity.Position;
import org.springframework.stereotype.Component;

@Component
public class PositionConverter implements Converter<Position, PositionDTO> {

    @Override
    public PositionDTO convertToDTO(Position position) {
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setCoordinateX(position.getCoordinateX());
        positionDTO.setCoordinateY(position.getCoordinateY());
        return positionDTO;
    }

    @Override
    public Position convertFromDTO(PositionDTO positionDTO) {
        Position position = new Position();
        position.setCoordinateX(positionDTO.getCoordinateX());
        position.setCoordinateY(positionDTO.getCoordinateY());
        return position;
    }
}
