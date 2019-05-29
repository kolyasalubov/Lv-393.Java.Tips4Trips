package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.Country;
import com.softserve.academy.Tips4Trips.entity.city.City;
import com.softserve.academy.Tips4Trips.entity.city.CityFeedback;
import com.softserve.academy.Tips4Trips.repository.CityFeedbackRepository;
import com.softserve.academy.Tips4Trips.repository.CityRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private static final Logger logger = Logger.getLogger(CityService.class);

    CityRepository cityRepository;
    CityFeedbackRepository cityFeedbackRepository;

    @Autowired
    public CityService(CityRepository cityRepository,
                       CityFeedbackRepository cityFeedbackRepository) {
        this.cityRepository = cityRepository;
        this.cityFeedbackRepository = cityFeedbackRepository;
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

    public Page<City> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    public Page<City> findByCountryId(Long countryId, Pageable pageable) {
        return cityRepository.findByCountryId(countryId, pageable);
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

    public Long getCount() {
        return cityRepository.count();
    }

    public Long getCountByCountryId(Long countryId) {
        return cityRepository.countByCountryId(countryId);
    }

    public City findByName(String name) {
        return cityRepository.findByName(name).orElse(null);
    }

    public double getCityRating(Long id) {
        return cityFeedbackRepository.findByCityId(id)
                .stream()
                .mapToDouble(CityFeedback::getAverageRating)
                .average()
                .orElse(0);
    }
}
