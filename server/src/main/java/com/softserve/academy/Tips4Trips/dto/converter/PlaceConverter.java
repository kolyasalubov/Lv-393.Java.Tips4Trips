package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.PlaceDTO;
import com.softserve.academy.Tips4Trips.entity.place.Place;
import org.springframework.stereotype.Component;

@Component
public class PlaceConverter implements Converter<Place, PlaceDTO> {


    @Override
    public PlaceDTO convertToDTO(Place place) {
        return null;
    }

    @Override
    public Place convertFromDTO(PlaceDTO placeDTO) {
        return null;
    }
}
