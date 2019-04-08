package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.CityDTO;
import com.softserve.academy.Tips4Trips.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CityConverter implements Converter<City, CityDTO> {
    private PositionConverter positionConverter;
//    private CountryConverter countryConverter;
    private PlaceConverter placeConverter;

    @Autowired
    public CityConverter(PositionConverter positionConverter,
//                         CountryConverter countryConverter,
                         PlaceConverter placeConverter) {
        this.positionConverter = positionConverter;
//        this.countryConverter = countryConverter;
        this.placeConverter = placeConverter;
    }

    @Override
    public CityDTO apply(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setPosition(positionConverter.apply(city.getPosition()));
//        cityDTO.setCountry(countryConverter.apply(city.getCountry()));
        cityDTO.setListOfPlaces(city.getListOfPlaces().stream()
                .map(place -> placeConverter.apply(place))
                .collect(Collectors.toList())
        );
        return cityDTO;
    }

}
