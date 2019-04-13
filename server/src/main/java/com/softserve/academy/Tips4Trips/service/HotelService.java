package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.place.Hotel;
import com.softserve.academy.Tips4Trips.repository.CityRepository;
import com.softserve.academy.Tips4Trips.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private HotelRepository repository;
    private CityRepository cityRepository;

    @Autowired
    public HotelService(HotelRepository restaurantRepository, CityRepository cityRepository) {
        this.repository = restaurantRepository;
        this.cityRepository = cityRepository;
    }

    public List<Hotel> findAll() {
        return repository.findAll();
    }

    public Hotel findById(Long id) {
        Optional<Hotel> hotel = repository.findById(id);
        return hotel.orElse(null);
    }

    public List<Hotel> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Hotel> findByCity(String name){
        Optional<City> city = cityRepository.findByName(name);
        return city.map(city1 -> repository.findByCity(city1)).orElse(null);
    }

    public Hotel createHotel(Hotel hotel) {
        return repository.save(hotel);
    }

    public Hotel update(Hotel hotel) {
        return repository.save(hotel);
    }


}
