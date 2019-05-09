package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.converter.ImageConverter;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.place.Monument;
import com.softserve.academy.Tips4Trips.repository.CityRepository;
import com.softserve.academy.Tips4Trips.repository.MonumentRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonumentService {

    private static final Logger logger = Logger.getLogger(MonumentService.class);

    private MonumentRepository repository;
    private CityRepository cityRepository;

    @Autowired
    public MonumentService(MonumentRepository repository,
                           CityRepository cityRepository){
        this.repository = repository;
        this.cityRepository = cityRepository;
    }

    public Monument findById(Long id) {
        Optional<Monument> monument = repository.findById(id);
        return monument.orElse(null);
    }

    public List<Monument> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Monument> findByCity(Long id) {
        Optional<City> city = cityRepository.findById(id);
        return city.map(value -> repository.findByCity(value)).orElse(null);
    }

    public Monument createMonument(Monument monument) {
        return repository.save(monument);
    }

    public List<Monument> findAll() {
        return repository.findAll();
    }


    public Monument update(Monument monument) {
        Monument monumentToUpdate = repository.getOne(monument.getId());
        monumentToUpdate.setName(monument.getName());
        monumentToUpdate.setDescription(monument.getDescription());
        monumentToUpdate.setAddress(monument.getAddress());
        monumentToUpdate.setPosition(monument.getPosition());
        monumentToUpdate.setImage(monument.getImage());
        monumentToUpdate.setCity(monument.getCity());
        return repository.save(monumentToUpdate);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
