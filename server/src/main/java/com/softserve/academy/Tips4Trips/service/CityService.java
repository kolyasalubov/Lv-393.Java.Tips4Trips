package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.CityDTO;
import com.softserve.academy.Tips4Trips.dto.converter.CityConverter;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    CityRepository cityRepository;
    CityConverter cityConverter;

    @Autowired
    public CityService(CityRepository cityRepository,
                       CityConverter cityConverter) {
        this.cityRepository = cityRepository;
        this.cityConverter = cityConverter;
    }

    public City createCity(CityDTO cityDTO) {
        City city = cityConverter.convertToEntity(cityDTO);
        return cityRepository.save(city);
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