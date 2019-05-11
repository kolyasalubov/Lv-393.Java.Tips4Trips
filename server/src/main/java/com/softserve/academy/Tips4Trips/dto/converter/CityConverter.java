package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.controller.CityController;
import com.softserve.academy.Tips4Trips.dto.CityDTO;
import com.softserve.academy.Tips4Trips.dto.CityRatingDTO;
import com.softserve.academy.Tips4Trips.entity.Country;
import com.softserve.academy.Tips4Trips.entity.city.City;
import com.softserve.academy.Tips4Trips.repository.CountryRepository;
import com.softserve.academy.Tips4Trips.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityConverter implements Converter<City, CityDTO> {

    private CountryRepository countryRepository;
    private CityService cityService;

    @Autowired
    public CityConverter(CountryRepository countryRepository,
                         CityService cityService) {
        this.countryRepository = countryRepository;
        this.cityService = cityService;
    }

    public List<CityRatingDTO> convertToRatingDTO(List<City> cities) {
        return cities.stream().map(this::convertToRatingDTO).collect(Collectors.toList());
    }

    public CityRatingDTO convertToRatingDTO(City city) {
        CityDTO cityDTO = convertToDTO(city);
        CityRatingDTO cityRatingDTO = new CityRatingDTO(cityDTO);
        cityRatingDTO.setAverageRating(cityService.getCityRating(city.getId()));
        return cityRatingDTO;
    }

    @Override
    public CityDTO convertToDTO(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setPosition(city.getPosition());
        cityDTO.setCountryId(city.getCountry().getId());
        cityDTO.setPlaces(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(CityController.class)
                        .getAllByCityId(city.getId())
        ).withSelfRel().getHref().replace("{countryId}", city.getCountry().getId().toString()));
        return cityDTO;
    }

    @Override
    public City convertToEntity(CityDTO cityDTO) {
        City city = new City();
        city.setId(cityDTO.getId());
        city.setName(cityDTO.getName());
        city.setPosition(cityDTO.getPosition());
        Country country = countryRepository.findById(cityDTO.getCountryId()).get();
        city.setCountry(country);
        return city;
    }

}
