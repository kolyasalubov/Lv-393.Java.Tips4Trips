package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.CityFeedbackDTO;
import com.softserve.academy.Tips4Trips.entity.city.City;
import com.softserve.academy.Tips4Trips.entity.city.CityFeedback;
import com.softserve.academy.Tips4Trips.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityFeedbackConverter implements Converter<CityFeedback, CityFeedbackDTO> {

    private CityRepository cityRepository;

    @Autowired
    public CityFeedbackConverter(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public CityFeedbackDTO convertToDTO(CityFeedback cityFeedback) {
        CityFeedbackDTO cityFeedbackDTO = new CityFeedbackDTO();
        cityFeedbackDTO.setId(cityFeedback.getId());
        cityFeedbackDTO.setCityId(cityFeedback.getCity().getId());
        cityFeedbackDTO.setContent(cityFeedback.getContent());
        cityFeedbackDTO.setCreationDate(cityFeedback.getDate());
        cityFeedbackDTO.setStatus(cityFeedback.getStatus());
        cityFeedbackDTO.setAuthor(cityFeedback.getCreatedBy());
        cityFeedbackDTO.setWeatherRating(cityFeedback.getWeatherRating());
        cityFeedbackDTO.setSafetyRating(cityFeedback.getSafetyRating());
        cityFeedbackDTO.setTransportRating(cityFeedback.getTransportRating());
        cityFeedbackDTO.setCostOfLivingRating(cityFeedback.getCostOfLivingRating());
        cityFeedbackDTO.setEntertainmentRating(cityFeedback.getEntertainmentRating());
        cityFeedbackDTO.setAverageRating(cityFeedback.getAverageRating());
        return cityFeedbackDTO;
    }

    @Override
    public CityFeedback convertToEntity(CityFeedbackDTO cityFeedbackDTO) {
        CityFeedback cityFeedback = new CityFeedback();
        cityFeedback.setId(cityFeedbackDTO.getId());
        City city = cityRepository.findById(cityFeedbackDTO.getCityId()).get();
        cityFeedback.setCity(city);
        cityFeedback.setContent(cityFeedbackDTO.getContent());
        cityFeedback.setDate(cityFeedbackDTO.getCreationDate());
        cityFeedback.setStatus(cityFeedbackDTO.getStatus());
        cityFeedback.setCreatedBy(cityFeedbackDTO.getAuthor());
        cityFeedback.setWeatherRating(cityFeedbackDTO.getWeatherRating());
        cityFeedback.setSafetyRating(cityFeedbackDTO.getSafetyRating());
        cityFeedback.setTransportRating(cityFeedbackDTO.getTransportRating());
        cityFeedback.setCostOfLivingRating(cityFeedbackDTO.getCostOfLivingRating());
        cityFeedback.setEntertainmentRating(cityFeedbackDTO.getEntertainmentRating());
        return cityFeedback;
    }
}
