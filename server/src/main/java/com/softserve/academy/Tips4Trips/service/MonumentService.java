package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.MonumentDTO;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.place.Monument;
import com.softserve.academy.Tips4Trips.repository.CityRepository;
import com.softserve.academy.Tips4Trips.repository.MonumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonumentService {

    private MonumentRepository repository;
    private CityRepository cityRepository;

    @Autowired
    public MonumentService(MonumentRepository repository, CityRepository cityRepository){
        this.repository = repository;
        this.cityRepository = cityRepository;
    }

    public Monument findById(Long id) {
        Optional<Monument> monument = repository.findById(id);
        if (monument.isPresent()) {
            return monument.get();
        } else {
            return null;
        }
    }

    public List<Monument> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Monument> findByCity(String name) {
        Optional<City> city = cityRepository.findByName(name);
        if (city.isPresent()) {
            return repository.findByCity(city.get());
        } else {
            return null;
        }
    }

    public Monument createMonument(Monument monument) {
        return repository.save(monument);
    }

    public List<Monument> findAll() {
        return repository.findAll();
    }


    public Monument update(Monument monument) {
        return repository.save(monument);
    }
}
