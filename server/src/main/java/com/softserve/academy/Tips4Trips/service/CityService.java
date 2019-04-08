package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.CityDTO;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.repository.CityRepository;

public interface CityService extends Service<City, Long, CityRepository> {

    City createCity(CityDTO cityDTO);
}
