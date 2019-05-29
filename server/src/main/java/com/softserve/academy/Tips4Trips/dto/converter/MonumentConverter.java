package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.controller.MonumentController;
import com.softserve.academy.Tips4Trips.controller.RestaurantController;
import com.softserve.academy.Tips4Trips.dto.details.MonumentDetailsDTO;
import com.softserve.academy.Tips4Trips.entity.place.Monument;
import com.softserve.academy.Tips4Trips.service.CityService;
import com.softserve.academy.Tips4Trips.service.MonumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class MonumentConverter implements Converter<Monument, MonumentDetailsDTO> {

    private final int MAX_DESCRIPTION_LENGTH = 100;
    private final CityService cityService;
    private final CityConverter cityConverter;
    private final ImageConverter imageConverter;

    @Autowired
    public MonumentConverter(ImageConverter imageConverter,
                             CityService cityService,
                             CityConverter cityConverter) {
        this.cityService = cityService;
        this.cityConverter = cityConverter;
        this.imageConverter = imageConverter;
    }

    @Override
    public Monument convertToEntity(MonumentDetailsDTO monumentDetailsDTO) {
        Monument monument = new Monument();
        monument.setId(monumentDetailsDTO.getId());
        monument.setName(monumentDetailsDTO.getName());
        monument.setDescription(monumentDetailsDTO.getDescription());
        monument.setAddress(monumentDetailsDTO.getAddress());
        monument.setPosition(monumentDetailsDTO.getPosition());
        monument.setImage(imageConverter
                .convertToEntity(monumentDetailsDTO.getImage()));
        monument.setCity(cityConverter
                .convertToEntity(monumentDetailsDTO.getCityDTO()));
        return monument;
    }

    @Override
    public MonumentDetailsDTO convertToDTO(Monument monument) {
        MonumentDetailsDTO monumentDetailsDTO = new MonumentDetailsDTO();

        monumentDetailsDTO.setId(monument.getId());
        monumentDetailsDTO.setName(monument.getName());
        monumentDetailsDTO.setDescription(monument.getDescription());
        monumentDetailsDTO.setAddress(monument.getAddress());
        monumentDetailsDTO.setPosition(monument.getPosition());
        monumentDetailsDTO.setImage(imageConverter
                .convertToDTO(monument.getImage()));
        monumentDetailsDTO.setCityDTO(cityConverter.convertToDTO(cityService
                .findById(monument.getCity().getId())));
        monumentDetailsDTO.setCategory(monument.getCategory());
        monumentDetailsDTO.setSelf(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(MonumentController.class)
                        .getById(monument.getId())
        ).withSelfRel().getHref().replace("{countryId}", monument.getCity().getCountry().getId().toString())
                .replace("{cityId}", monument.getCity().getId().toString()));

        return monumentDetailsDTO;
    }


}
