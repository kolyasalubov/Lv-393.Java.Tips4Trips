package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.CityDTO;
import com.softserve.academy.Tips4Trips.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CityConverter implements Converter<City, CityDTO> {
    private PositionConverter positionConverter;
    private PlaceConverter placeConverter;

    @Autowired
    public CityConverter(PositionConverter positionConverter,
                         PlaceConverter placeConverter) {
        this.positionConverter = positionConverter;
        this.placeConverter = placeConverter;
    }


    @Override
    public CityDTO convertToDTO(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setPosition(positionConverter.convertToDTO(city.getPosition()));
        cityDTO.setListOfPlaces(city.getListOfPlaces().stream()
                .map(place -> placeConverter.convertToDTO(place))
                .collect(Collectors.toList())
        );
        return cityDTO;
    }

    @Override
    public City convertToEntity(CityDTO cityDTO) {
        City city = new City();
        city.setId(cityDTO.getId());
        city.setName(cityDTO.getName());
        city.setPosition(positionConverter.convertToEntity(cityDTO.getPosition()));
        if (cityDTO.getListOfPlaces() != null && !cityDTO.getListOfPlaces().isEmpty()) {
            city.setListOfPlaces(cityDTO.getListOfPlaces().stream()
                    .map(placeDTO -> placeConverter.convertToEntity(placeDTO))
                    .collect(Collectors.toList())
            );
        }
        return city;
    }

}
