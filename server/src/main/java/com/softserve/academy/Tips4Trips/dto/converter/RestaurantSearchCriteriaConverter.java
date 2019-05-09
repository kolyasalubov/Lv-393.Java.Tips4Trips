package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.search.RestaurantSearchCriteria;
import com.softserve.academy.Tips4Trips.dto.search.RestaurantSearchCriteriaDTO;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Component
public class RestaurantSearchCriteriaConverter {

    private final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());

    public RestaurantSearchCriteria convertToEntity(RestaurantSearchCriteriaDTO criteriaDTO) {
        RestaurantSearchCriteria criteria = new RestaurantSearchCriteria();
        criteria.setCityId(criteriaDTO.getCityId());
        try {
            criteria.setWorksAt(formatter.parse(criteriaDTO.getWorksAt()));
        } catch (ParseException e) {
        }
        criteria.setAverageBill(criteriaDTO.getAverageBill());
        criteria.setHasVeganFood(criteriaDTO.getHasVeganFood());
        return criteria;
    }

}
