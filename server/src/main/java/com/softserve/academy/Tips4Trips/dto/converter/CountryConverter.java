package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.controller.CityController;
import com.softserve.academy.Tips4Trips.controller.CountryController;
import com.softserve.academy.Tips4Trips.dto.CountryDTO;
import com.softserve.academy.Tips4Trips.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CountryConverter implements Converter<Country, CountryDTO> {

    private CityConverter cityConverter;

    @Autowired
    public CountryConverter(CityConverter cityConverter) {
        this.cityConverter = cityConverter;
    }

    @Override
    public CountryDTO convertToDTO(Country country) {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setId(country.getId());
        countryDTO.setName(country.getName());
        countryDTO.setPosition(country.getPosition());
        countryDTO.setCities(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(CityController.class)
                        .getAllByCountryId(country.getId())
        ).withSelfRel().getHref());
        return countryDTO;
    }

    @Override
    public Country convertToEntity(CountryDTO countryDTO) {
        Country country = new Country();
        country.setId(countryDTO.getId());
        country.setName(countryDTO.getName());
        country.setPosition(countryDTO.getPosition());
        return country;
    }
}
