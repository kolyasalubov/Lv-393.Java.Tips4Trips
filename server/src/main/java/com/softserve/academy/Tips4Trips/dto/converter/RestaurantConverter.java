package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.RestaurantDTO;
import com.softserve.academy.Tips4Trips.entity.place.Restaurant;
import com.softserve.academy.Tips4Trips.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantConverter implements Converter<Restaurant, RestaurantDTO> {

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantConverter (RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @Override
    public RestaurantDTO convertToDTO(Restaurant restaurant) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setDescription(restaurant.getDescription());
        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setPosition(restaurant.getPosition());
        restaurantDTO.setPhotoPath(restaurant.getPhotoPath());
        restaurantDTO.setCityId(restaurant.getCity().getId());
        restaurantDTO.setAverageBill(restaurant.getAverageBill());
        restaurantDTO.setHasVeganFood(restaurant.getHasVeganFood());

        return restaurantDTO;
    }

    @Override
    public Restaurant convertToEntity(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDTO.getId());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setDescription(restaurantDTO.getDescription());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setPosition(restaurantDTO.getPosition());
        restaurant.setPhotoPath(restaurantDTO.getPhotoPath());
        restaurant.setAverageBill(restaurantDTO.getAverageBill());
        restaurant.setHasVeganFood(restaurantDTO.getHasVeganFood());
        return restaurant;
    }


}
