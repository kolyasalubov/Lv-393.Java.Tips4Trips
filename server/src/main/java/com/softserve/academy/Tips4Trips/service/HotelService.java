package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.constants.ExceptionMessages;
import com.softserve.academy.Tips4Trips.dto.search.HotelSearchCriteria;
import com.softserve.academy.Tips4Trips.entity.city.City;
import com.softserve.academy.Tips4Trips.entity.place.Hotel;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;
import com.softserve.academy.Tips4Trips.repository.CityRepository;
import com.softserve.academy.Tips4Trips.repository.HotelRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.softserve.academy.Tips4Trips.specifications.HotelSpecifications.*;

@Service
public class HotelService {

    private static final Logger logger = Logger.getLogger(HotelService.class);
    private final HotelRepository repository;

    @Autowired
    public HotelService(HotelRepository restaurantRepository) {
        this.repository = restaurantRepository;
    }

    public List<Hotel> findAll() {
        if (!repository.findAll().isEmpty()) {
            return repository.findAll();
        } else {
            throw new DataNotFoundException(ExceptionMessages.HOTELS_DO_NOT_EXIST);
        }
    }

    public Hotel findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new DataNotFoundException(ExceptionMessages.HOTEL_BY_THIS_ID_IS_NOT_FOUND));
    }

    public List<Hotel> findByName(String name) {
        if (!repository.findByName(name).isEmpty()) {
            return repository.findByName(name);
        } else {
            throw new DataNotFoundException(ExceptionMessages.HOTEL_WITH_SUCH_NAME_DO_NOT_EXIST);
        }
    }

    @Transactional
    public Hotel createHotel(Hotel hotel) {
        return repository.save(hotel);
    }

    @Transactional
    public Hotel update(Hotel hotel) {
        return repository.save(hotel);
    }

    public List<Hotel> filter(HotelSearchCriteria criteria) {
        return repository.findAll(Specification.where(city(criteria.getCityId()))
                .and(inOpeningTime(criteria.getWorksAt()))
                .and(inClosingTime(criteria.getWorksAt()))
                .and(priceIn(criteria.getPriceIn())));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
