package com.softserve.academy.Tips4Trips.dto.converter.reverse;

import com.softserve.academy.Tips4Trips.dto.CountryDTO;
import com.softserve.academy.Tips4Trips.dto.converter.Converter;
import com.softserve.academy.Tips4Trips.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ReverseCountryConverter implements Converter<CountryDTO, Country> {

    ReversePositionConverter reversePositionConverter;
    ReverseCityConverter reverseCityConverter;

    @Autowired
    public ReverseCountryConverter(ReversePositionConverter reversePositionConverter,
                                   ReverseCityConverter reverseCityConverter) {
        this.reversePositionConverter = reversePositionConverter;
        this.reverseCityConverter = reverseCityConverter;
    }

    @Override
    public Country apply(CountryDTO countryDTO) {
        Country country = new Country();
        country.setName(countryDTO.getName());
        if (countryDTO.getPosition() != null) {
            country.setPosition(reversePositionConverter.apply(countryDTO.getPosition()));
        }
        if (countryDTO.getListOfCities() != null && !countryDTO.getListOfCities().isEmpty()) {
            country.setListOfCities(countryDTO.getListOfCities().stream()
                    .map(cityDTO -> reverseCityConverter.apply(cityDTO))
                    .collect(Collectors.toList())
            );
        }
        return country;
    }
}
