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
        City city = cityConverter.convertFromDTO(cityDTO);
        return cityRepository.save(city);
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }
}