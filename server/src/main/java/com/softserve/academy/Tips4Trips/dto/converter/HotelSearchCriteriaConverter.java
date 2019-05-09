package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.search.HotelSearchCriteria;
import com.softserve.academy.Tips4Trips.dto.search.HotelSearchCriteriaDTO;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Component
public class HotelSearchCriteriaConverter {

    private final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());

    public HotelSearchCriteria convertToEntity(HotelSearchCriteriaDTO criteriaDTO) {
        HotelSearchCriteria criteria = new HotelSearchCriteria();
        criteria.setCityId(criteriaDTO.getCityId());
        try {
            criteria.setWorksAt(formatter.parse(criteriaDTO.getWorksAt()));
        } catch (ParseException e) {
        }
        criteria.setPriceIn(criteriaDTO.getPriceIn());
        return criteria;
    }

}
