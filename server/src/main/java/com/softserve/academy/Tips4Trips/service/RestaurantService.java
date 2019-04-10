package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.place.Restaurant;
import com.softserve.academy.Tips4Trips.repository.CityRepository;
import com.softserve.academy.Tips4Trips.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    private RestaurantRepository repository;
    private CityRepository cityRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, CityRepository cityRepository) {
        this.repository = restaurantRepository;
        this.cityRepository = cityRepository;
    }

    public Optional<Restaurant> findById(Long id) {
        return repository.findById(id);
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
}
