package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.controller.RestaurantController;
import com.softserve.academy.Tips4Trips.dto.details.RestaurantDetailsDTO;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.place.Restaurant;
import com.softserve.academy.Tips4Trips.service.CityService;
import com.softserve.academy.Tips4Trips.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Component
public class RestaurantConverter implements Converter<Restaurant, RestaurantDetailsDTO> {

    private RestaurantService restaurantService;
    private CityService cityService;
    private CityConverter cityConverter;
    private PlaceConverter placeConverter;
    private final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());

    @Autowired
    public RestaurantConverter (RestaurantService restaurantService, CityService cityService, CityConverter cityConverter, PlaceConverter placeConverter) {
        this.restaurantService = restaurantService;
        this.cityService = cityService;
        this.cityConverter = cityConverter;
        this.placeConverter = placeConverter;
    }


    @Override
    public Restaurant convertToEntity(RestaurantDetailsDTO restaurantDetailsDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDetailsDTO.getId());
        restaurant.setName(restaurantDetailsDTO.getName());
        restaurant.setDescription(restaurantDetailsDTO.getDescription());
        restaurant.setAddress(restaurantDetailsDTO.getAddress());
        restaurant.setPosition(restaurantDetailsDTO.getPosition());
        restaurant.setPhotoPath(restaurantDetailsDTO.getPhotoPath());
        restaurant.setCity(cityConverter.convertToEntity(restaurantDetailsDTO.getCityDTO()));
        restaurant.setWorkingDays(restaurantDetailsDTO.getWorkingDays());
        restaurant.setWebSite(restaurantDetailsDTO.getWebSite());
        restaurant.setTelephone(restaurantDetailsDTO.getTelephone());
        try {
            restaurant.setOpeningTime(formatter.parse(restaurantDetailsDTO.getOpeningTime()));
            restaurant.setClosingTime(formatter.parse(restaurantDetailsDTO.getClosingTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        restaurant.setAverageBill(restaurantDetailsDTO.getAverageBill());
        restaurant.setHasVeganFood(restaurantDetailsDTO.getHasVeganFood());
        return restaurant;
    }

    @Override
    public RestaurantDetailsDTO convertToDTO(Restaurant restaurant) {
        RestaurantDetailsDTO restaurantDetailsDTO = new RestaurantDetailsDTO();

        restaurantDetailsDTO.setId(restaurant.getId());
        restaurantDetailsDTO.setName(restaurant.getName());
        restaurantDetailsDTO.setDescription(restaurant.getDescription());
        restaurantDetailsDTO.setAddress(restaurant.getAddress());
        restaurantDetailsDTO.setWorkingDays(restaurant.getWorkingDays());
        restaurantDetailsDTO.setWebSite(restaurant.getWebSite());
        restaurantDetailsDTO.setTelephone(restaurant.getTelephone());
        restaurantDetailsDTO.setOpeningTime(formatter.format(restaurant.getOpeningTime()));
        restaurantDetailsDTO.setClosingTime(formatter.format(restaurant.getClosingTime()));
        restaurantDetailsDTO.setPhotoPath(restaurant.getPhotoPath());
        restaurantDetailsDTO.setPosition(restaurant.getPosition());
        restaurantDetailsDTO.setPhotoPath(restaurant.getPhotoPath());
        restaurantDetailsDTO.setCityDTO(cityConverter.convertToDTO(cityService.findById(restaurant.getCity().getId())));
        restaurantDetailsDTO.setAverageBill(restaurant.getAverageBill());
        restaurantDetailsDTO.setHasVeganFood(restaurant.getHasVeganFood());
        restaurantDetailsDTO.setCategory(restaurant.getCategory());
        restaurantDetailsDTO.setSelf(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(RestaurantController.class)
                        .getById(restaurant.getId())
        ).withSelfRel().getHref().replace("{countryId}", restaurant.getCity().getCountry().getId().toString())
                .replace("{cityId}", restaurant.getCity().getId().toString()));

        return restaurantDetailsDTO;
    }


}
