package com.softserve.academy.Tips4Trips.dto.converter.reverse;

import com.softserve.academy.Tips4Trips.dto.PlaceDTO;
import com.softserve.academy.Tips4Trips.dto.converter.Converter;
import com.softserve.academy.Tips4Trips.entity.place.Place;
import org.springframework.stereotype.Component;

@Component
public class ReversePlaceConverter implements Converter<PlaceDTO, Place> {
    @Override
    public Place apply(PlaceDTO placeDTO) {
        return null;
    }
}
