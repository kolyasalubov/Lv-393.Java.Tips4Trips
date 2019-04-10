package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.CityDTO;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityConverter implements Converter<City, CityDTO> {
    private PositionConverter positionConverter;

    @Autowired
    public CityConverter(PositionConverter positionConverter) {
        this.positionConverter = positionConverter;
    }

    @Override
    public CityDTO convertToDTO(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setPosition(positionConverter.convertToDTO(city.getPosition()));
        cityDTO.setCountryId(city.getCountry().getId());
        return cityDTO;
    }

    @Override
    public City convertToEntity(CityDTO cityDTO) {
        City city = new City();
        city.setId(cityDTO.getId());
        city.setName(cityDTO.getName());
        city.setPosition(positionConverter.convertToEntity(cityDTO.getPosition()));
        city.setCountry(new Country(cityDTO.getCountryId()));
        return city;
    }

}
