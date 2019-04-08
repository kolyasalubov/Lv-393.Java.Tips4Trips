package com.softserve.academy.Tips4Trips.service.impl;

import com.softserve.academy.Tips4Trips.dto.CityDTO;
import com.softserve.academy.Tips4Trips.dto.converter.reverse.ReverseCityConverter;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.repository.CityRepository;
import com.softserve.academy.Tips4Trips.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl extends ServiceImpl<City, Long, CityRepository> implements CityService {

    ReverseCityConverter reverseCityConverter;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository,
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