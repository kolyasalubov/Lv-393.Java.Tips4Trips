package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.search.HotelSearchCriteria;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.place.Hotel;
import com.softserve.academy.Tips4Trips.repository.CityRepository;
import com.softserve.academy.Tips4Trips.repository.HotelRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.softserve.academy.Tips4Trips.specifications.HotelSpecifications.*;

@Service
public class HotelService {

    private static final Logger logger = Logger.getLogger(HotelService.class);
    private final HotelRepository repository;
    private final CityRepository cityRepository;

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
        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            return null;
        }
    }

    public List<Hotel> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Hotel> findByCity(String name){
        Optional<City> city = cityRepository.findByName(name);
        if (city.isPresent()) {
            return repository.findByCity(city.get());
        } else {
            return null;
        }
    }

    public Hotel createHotel(Hotel hotel) {
        return repository.save(hotel);
    }

    public Hotel update(Hotel hotel) {
        return repository.save(hotel);
    }

    public List<Hotel> filter(HotelSearchCriteria criteria) {
        return repository.findAll(Specification.where(city(criteria.getCityId()))
                .and(inOpeningTime(criteria.getWorksAt()))
                .and(inClosingTime(criteria.getWorksAt()))
                .and(priceIn(criteria.getPriceIn())));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
