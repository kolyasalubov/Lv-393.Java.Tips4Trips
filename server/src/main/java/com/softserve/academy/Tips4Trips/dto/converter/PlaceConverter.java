package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.controller.HotelController;
import com.softserve.academy.Tips4Trips.controller.MonumentController;
import com.softserve.academy.Tips4Trips.controller.RestaurantController;
import com.softserve.academy.Tips4Trips.dto.MiniPlaceDTO;
import com.softserve.academy.Tips4Trips.dto.info.PlaceInfoDTO;
import com.softserve.academy.Tips4Trips.entity.place.Hotel;
import com.softserve.academy.Tips4Trips.entity.place.Monument;
import com.softserve.academy.Tips4Trips.entity.place.Place;
import com.softserve.academy.Tips4Trips.entity.place.Restaurant;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaceConverter implements Converter<Place, PlaceInfoDTO> {

    private final int MAX_DESCRIPTION_LENGTH = 100;

    @Override
    public PlaceInfoDTO convertToDTO(Place place) {
        return toInfoDTO(new PlaceInfoDTO(), place);
    }

    @Override
    public Place convertToEntity(PlaceInfoDTO placeInfoDTO) {
        return null;
    }

    public PlaceInfoDTO convertToInfoDTO(Place place) {
        return toInfoDTO(new PlaceInfoDTO(), place);
    }

    public List<PlaceInfoDTO> convertToInfoDTO(final List<? extends Place> places) {
        List<PlaceInfoDTO> dtos = new ArrayList<>();
        if (places != null) {
            dtos = places.stream().map(this::convertToInfoDTO).collect(Collectors.toList());
        }
        return dtos;
    }

    public MiniPlaceDTO convertToMiniDTO(Place place) {
        MiniPlaceDTO miniPlaceDTO = new MiniPlaceDTO();
        miniPlaceDTO.setId(place.getId());
        miniPlaceDTO.setName(place.getName());
        miniPlaceDTO.setCityName(place.getCity().getName());
        return miniPlaceDTO;
    }

    private PlaceInfoDTO toInfoDTO(PlaceInfoDTO placeInfoDTO, Place place) {
        placeInfoDTO.setId(place.getId());
        placeInfoDTO.setName(place.getName());
        String content = place.getDescription();
        String description = content.length() > MAX_DESCRIPTION_LENGTH
                ? content.substring(0, MAX_DESCRIPTION_LENGTH) : content;
        placeInfoDTO.setDescription(description);
        placeInfoDTO.setSelf(getSelfLink(place));
        placeInfoDTO.setCategory(place.getCategory());
        placeInfoDTO.setPosition(place.getPosition());
        return placeInfoDTO;
    }

    private String getSelfLink(Place place) {
        if (place.getClass().equals(Restaurant.class)) {
            return ControllerLinkBuilder.linkTo(
                    ControllerLinkBuilder.methodOn(RestaurantController.class)
                            .getById(place.getId())
            ).withSelfRel().getHref().replace("{countryId}", place.getCity().getCountry().getId().toString())
                    .replace("{cityId}", place.getCity().getId().toString());
        } else if (place.getClass().equals(Hotel.class)) {
            return ControllerLinkBuilder.linkTo(
                    ControllerLinkBuilder.methodOn(HotelController.class)
                            .getById(place.getId())
            ).withSelfRel().getHref().replace("{countryId}", place.getCity().getCountry().getId().toString())
                    .replace("{cityId}", place.getCity().getId().toString());
        } else if (place.getClass().equals(Monument.class)) {
            return ControllerLinkBuilder.linkTo(
                    ControllerLinkBuilder.methodOn(MonumentController.class)
                            .getById(place.getId())
            ).withSelfRel().getHref().replace("{countryId}", place.getCity().getCountry().getId().toString())
                    .replace("{cityId}", place.getCity().getId().toString());
        }
        return "";
    }
}
