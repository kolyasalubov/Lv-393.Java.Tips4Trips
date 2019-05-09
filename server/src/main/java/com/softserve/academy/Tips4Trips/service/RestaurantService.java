package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.search.RestaurantSearchCriteria;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.place.Restaurant;
import com.softserve.academy.Tips4Trips.repository.CityRepository;
import com.softserve.academy.Tips4Trips.repository.RestaurantRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.softserve.academy.Tips4Trips.specifications.RestaurantSpecifications.*;

@Service
public class RestaurantService {

    private static final Logger logger = Logger.getLogger(RestaurantService.class);

    private RestaurantRepository repository;
    private CityRepository cityRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, CityRepository cityRepository) {
        this.repository = restaurantRepository;
        this.cityRepository = cityRepository;
    }

    public List<Restaurant> findAll() {
        return repository.findAll();
    }

    public Restaurant findById(Long id) {
        Optional<Restaurant> monument = repository.findById(id);
        return monument.orElse(null);
    }

    public List<Restaurant> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Restaurant> findByCity(String name){
        Optional<City> city = cityRepository.findByName(name);
        if (city.isPresent()) {
            return repository.findByCity(city.get());
        } else {
            return null;
        }
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
        Restaurant restaurantToUpdate = repository.getOne(restaurant.getId());
        restaurantToUpdate.setId(restaurant.getId());
        restaurantToUpdate.setName(restaurant.getName());
        restaurantToUpdate.setDescription(restaurant.getDescription());
        restaurantToUpdate.setAddress(restaurant.getAddress());
        restaurantToUpdate.setPosition(restaurant.getPosition());
        restaurantToUpdate.setPhotoPath(restaurant.getPhotoPath());
        restaurantToUpdate.setCity(restaurant.getCity());
        restaurantToUpdate.setWorkingDays(restaurant.getWorkingDays());
        restaurantToUpdate.setWebSite(restaurant.getWebSite());
        restaurantToUpdate.setOpeningTime(restaurant.getOpeningTime());
        restaurantToUpdate.setClosingTime(restaurant.getClosingTime());
        restaurantToUpdate.setAverageBill(restaurant.getAverageBill());
        restaurantToUpdate.setHasVeganFood(restaurant.getHasVeganFood());
        return repository.save(restaurantToUpdate);
    }

    public List<Restaurant> filter(RestaurantSearchCriteria criteria) {
        return repository.findAll(Specification.where(city(criteria.getCityId()))
                .and(inOpeningTime(criteria.getWorksAt()))
                .and(inClosingTime(criteria.getWorksAt()))
                .and(averageBill(criteria.getAverageBill()))
                .and(hasVeganFood(criteria.getHasVeganFood())));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
