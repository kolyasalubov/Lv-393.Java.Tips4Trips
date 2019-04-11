package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.RestaurantDTO;
import com.softserve.academy.Tips4Trips.entity.place.Restaurant;
import com.softserve.academy.Tips4Trips.service.CityService;
import com.softserve.academy.Tips4Trips.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantConverter implements Converter<Restaurant, RestaurantDTO> {

    private RestaurantService restaurantService;
    private CityService cityService;

    @Autowired
    public RestaurantConverter (RestaurantService restaurantService, CityService cityService) {
        this.restaurantService = restaurantService;
        this.cityService = cityService;
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
        restaurantDTO.setWorkingDays(restaurant.getWorkingDays());
        restaurantDTO.setWebSite(restaurant.getWebSite());
        restaurantDTO.setTelephone(restaurant.getTelephone());
        restaurantDTO.setOpeningTime(restaurant.getOpeningTime());
        restaurantDTO.setClosingTime(restaurant.getClosingTime());
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
        restaurant.setCity(cityService.findById(restaurantDTO.getCityId()));
        restaurant.setWorkingDays(restaurantDTO.getWorkingDays());
        restaurant.setWebSite(restaurantDTO.getWebSite());
        restaurant.setType(restaurantDTO.getType());
        restaurant.setOpeningTime(restaurantDTO.getOpeningTime());
        restaurant.setClosingTime(restaurantDTO.getClosingTime());
        restaurant.setAverageBill(restaurantDTO.getAverageBill());
        restaurant.setHasVeganFood(restaurantDTO.getHasVeganFood());
        return restaurant;
    }


}
