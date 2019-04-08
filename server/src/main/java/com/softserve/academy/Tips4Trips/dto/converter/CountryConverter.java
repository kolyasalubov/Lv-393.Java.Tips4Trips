package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.CountryDTO;
import com.softserve.academy.Tips4Trips.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CountryConverter implements Converter<Country, CountryDTO> {

    private PositionConverter positionConverter;
    private CityConverter cityConverter;

    @Autowired
    public CountryConverter(PositionConverter positionConverter,
                            CityConverter cityConverter) {
        this.positionConverter = positionConverter;
        this.cityConverter = cityConverter;
    }

    @Override
    public CountryDTO apply(Country country) {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setId(country.getId());
        countryDTO.setName(country.getName());
        if (country.getPosition() != null) {
            countryDTO.setPosition(positionConverter.apply(country.getPosition()));
        }
        if (country.getListOfCities() != null && !country.getListOfCities().isEmpty()) {
            countryDTO.setListOfCities(country.getListOfCities().stream()
                    .map(city -> cityConverter.apply(city))
                    .collect(Collectors.toList())
            );
        }
        return countryDTO;
    }
}
