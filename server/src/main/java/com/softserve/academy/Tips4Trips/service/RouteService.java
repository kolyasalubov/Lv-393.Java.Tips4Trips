package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private RouteRepository repository;

    @Autowired
    public RouteService(RouteRepository repository) {
        this.repository = repository;
    }

    public List<Route> findAll() {
        return repository.findAll();
    }

    public Optional<Route> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Route route) {
        repository.delete(route);
    }
}
