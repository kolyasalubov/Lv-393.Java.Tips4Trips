package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.place.Place;
import com.softserve.academy.Tips4Trips.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PlaceService {

    PlaceRepository repository;

    @Autowired
    public PlaceService(PlaceRepository repository) {
        this.repository = repository;
    }

    public Place findById(Long id) {
        Optional<Place> place = repository.findById(id);
        if (place.isPresent()) {
            return place.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<Place> findByCity(City city) {
        return repository.findByCity(city);
    }
}
