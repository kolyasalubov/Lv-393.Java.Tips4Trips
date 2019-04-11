package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.CityDTO;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.Country;
<<<<<<< HEAD
=======
import com.softserve.academy.Tips4Trips.repository.CountryRepository;
>>>>>>> b1ea33e4270bc337543610df172bca2d23e6076a
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityConverter implements Converter<City, CityDTO> {
<<<<<<< HEAD
    private PositionConverter positionConverter;

    @Autowired
    public CityConverter(PositionConverter positionConverter) {
        this.positionConverter = positionConverter;
=======

    private CountryRepository countryRepository;

    @Autowired
    public CityConverter(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
>>>>>>> b1ea33e4270bc337543610df172bca2d23e6076a
    }

    @Override
    public CityDTO convertToDTO(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
<<<<<<< HEAD
        cityDTO.setPosition(positionConverter.convertToDTO(city.getPosition()));
=======
        cityDTO.setPosition(city.getPosition());
>>>>>>> b1ea33e4270bc337543610df172bca2d23e6076a
        cityDTO.setCountryId(city.getCountry().getId());
        return cityDTO;
    }

    @Override
    public City convertToEntity(CityDTO cityDTO) {
        City city = new City();
        city.setId(cityDTO.getId());
        city.setName(cityDTO.getName());
<<<<<<< HEAD
        city.setPosition(positionConverter.convertToEntity(cityDTO.getPosition()));
        city.setCountry(new Country(cityDTO.getCountryId()));
=======
        city.setPosition(cityDTO.getPosition());
        Country country = countryRepository.findById(cityDTO.getCountryId()).get();
        city.setCountry(country);
>>>>>>> b1ea33e4270bc337543610df172bca2d23e6076a
        return city;
    }

}
