package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.CityDTO;
import com.softserve.academy.Tips4Trips.dto.converter.reverse.ReverseCityConverter;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService extends ServiceImpl<City, Long, CityRepository> implements CityService {

    ReverseCityConverter reverseCityConverter;

    @Autowired
    public CityService(CityRepository cityRepository,
                       ReverseCityConverter reverseCityConverter) {
        super(cityRepository);
        this.reverseCityConverter = reverseCityConverter;
    }

    @Override
    public City createCity(CityDTO cityDTO) {
        City city = reverseCityConverter.convert(cityDTO);
        return repository.save(city);
    }
}