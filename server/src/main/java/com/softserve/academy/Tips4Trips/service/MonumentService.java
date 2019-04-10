package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.MonumentDTO;
import com.softserve.academy.Tips4Trips.entity.place.Monument;
import com.softserve.academy.Tips4Trips.repository.MonumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonumentService {

    private MonumentRepository repository;

    @Autowired
    public MonumentService(MonumentRepository repository){
        this.repository = repository;
    }

    public Optional<Monument> findById(Long id) {
        return repository.findById(id);
    }

    public List<Monument> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Monument> findByCityAndName(String city, String name) {
        return repository.findByCityAndName(city, name);
    }

    public Monument createMonument(Monument monument) {
        return repository.save(monument);
    }

    public Monument update(MonumentDTO monumentDTO) {
        Monument monument = repository.findById(monumentDTO.getId()).get();
        monument.setName(monumentDTO.getName());
        monument.setDescription(monumentDTO.getDescription());
        monument.setAddress(monumentDTO.getAddress());
        monument.setPosition(monumentDTO.getPosition());
        monument.setPhotoPath(monumentDTO.getPhotoPath());
        return repository.save(monument);
    }
}
