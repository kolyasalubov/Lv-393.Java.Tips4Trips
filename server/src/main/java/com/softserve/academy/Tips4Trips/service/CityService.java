package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.Country;
import com.softserve.academy.Tips4Trips.entity.place.Place;
import com.softserve.academy.Tips4Trips.repository.CityRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private static final Logger logger = Logger.getLogger(CityService.class);

    CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public List<City> findByCountry(Country country) {
        return cityRepository.findByCountry(country);
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public void deleteById(Long id) {
        cityRepository.findById(id).ifPresent(cityRepository::delete);
    }

    public City update(City city) {
        if (city.getId() == null) {
            throw new IllegalArgumentException();
        }
        return cityRepository.save(city);
    }

    public City findById(Long id) {
        return cityRepository.findById(id).get();
    }
}
