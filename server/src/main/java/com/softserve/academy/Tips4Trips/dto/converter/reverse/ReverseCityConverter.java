package com.softserve.academy.Tips4Trips.dto.converter.reverse;

import com.softserve.academy.Tips4Trips.dto.CityDTO;
import com.softserve.academy.Tips4Trips.dto.converter.Converter;
import com.softserve.academy.Tips4Trips.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ReverseCityConverter implements Converter<CityDTO, City> {

    private ReversePlaceConverter reversePlaceConverter;
//    private ReverseCountryConverter reverseCountryConverter;

    @Autowired
    public ReverseCityConverter(ReversePlaceConverter reversePlaceConverter)
//                                ReverseCountryConverter reverseCountryConverter)
    {
        this.reversePlaceConverter = reversePlaceConverter;
//        this.reverseCountryConverter = reverseCountryConverter;
    }

    @Override
    public City apply(CityDTO cityDTO) {
        City city = new City();
        city.setName(cityDTO.getName());
//        city.setCountry(reverseCountryConverter.apply(cityDTO.getCountry()));
        if (cityDTO.getListOfPlaces() != null && !cityDTO.getListOfPlaces().isEmpty()) {
            city.setListOfPlaces(cityDTO.getListOfPlaces().stream()
                    .map(placeDTO -> reversePlaceConverter.apply(placeDTO))
                    .collect(Collectors.toList())
            );
        }
        return city;
    }
}
