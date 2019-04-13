package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.details.RestaurantDetailsDTO;
import com.softserve.academy.Tips4Trips.entity.place.Restaurant;
import com.softserve.academy.Tips4Trips.service.CityService;
import com.softserve.academy.Tips4Trips.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantConverter implements Converter<Restaurant, RestaurantDetailsDTO> {

    private RestaurantService restaurantService;
    private CityService cityService;
    private CityConverter cityConverter;
    private PlaceConverter placeConverter;
    private final int MAX_DESCRIPTION_LENGTH = 100;

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
        restaurant.setType(restaurantDetailsDTO.getType());
        restaurant.setOpeningTime(restaurantDetailsDTO.getOpeningTime());
        restaurant.setClosingTime(restaurantDetailsDTO.getClosingTime());
        restaurant.setAverageBill(restaurantDetailsDTO.getAverageBill());
        restaurant.setHasVeganFood(restaurantDetailsDTO.getHasVeganFood());
        return restaurant;
    }

    @Override
    public RestaurantDetailsDTO convertToDTO(Restaurant restaurant) {
        RestaurantDetailsDTO restaurantDetailsDTO = (RestaurantDetailsDTO)
                placeConverter.convertToInfoDTO(restaurant);

        restaurantDetailsDTO.setId(restaurant.getId());
        restaurantDetailsDTO.setName(restaurant.getName());

        restaurantDetailsDTO.setDescription(restaurant.getDescription());
        restaurantDetailsDTO.setAddress(restaurant.getAddress());
        restaurantDetailsDTO.setWorkingDays(restaurant.getWorkingDays());
        restaurantDetailsDTO.setWebSite(restaurant.getWebSite());
        restaurantDetailsDTO.setTelephone(restaurant.getTelephone());
        restaurantDetailsDTO.setType(restaurant.getType());
        restaurantDetailsDTO.setOpeningTime(restaurant.getOpeningTime());
        restaurantDetailsDTO.setClosingTime(restaurant.getClosingTime());
        restaurantDetailsDTO.setPhotoPath(restaurant.getPhotoPath());
        restaurantDetailsDTO.setPosition(restaurant.getPosition());
        restaurantDetailsDTO.setPhotoPath(restaurant.getPhotoPath());
        restaurantDetailsDTO.setCityDTO(cityConverter.convertToDTO(cityService.findById(restaurant.getCity().getId())));
        restaurantDetailsDTO.setAverageBill(restaurant.getAverageBill());
        restaurantDetailsDTO.setHasVeganFood(restaurant.getHasVeganFood());

        return restaurantDetailsDTO;
    }


}
