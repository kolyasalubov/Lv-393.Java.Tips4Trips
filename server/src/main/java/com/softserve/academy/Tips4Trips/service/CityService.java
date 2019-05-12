package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.Country;
import com.softserve.academy.Tips4Trips.entity.city.City;
import com.softserve.academy.Tips4Trips.entity.city.CityFeedback;
import com.softserve.academy.Tips4Trips.repository.CityFeedbackRepository;
import com.softserve.academy.Tips4Trips.repository.CityRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<City> findByCountryId(Long countryId) {
        return cityRepository.findByCountryId(countryId);
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

    public double getCityRating(Long id) {
        List<CityFeedback> feedbacks = cityFeedbackRepository.findByCityId(id);
        double avgRating = 0;
        if (feedbacks != null && !feedbacks.isEmpty()) {
            for (CityFeedback feedback : feedbacks) {
                double averageRating = feedback.getAverageRating();
                avgRating += averageRating;
            }
            avgRating /= feedbacks.size();
        }
        return avgRating;
    }
}
