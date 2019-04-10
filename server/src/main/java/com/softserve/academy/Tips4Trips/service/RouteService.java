package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private RouteRepository repository;
    private AccountRepository accountRepository;


    @Autowired
    public RouteService(RouteRepository repository,
                        AccountRepository accountRepository) {
        this.repository = repository;
        this.accountRepository = accountRepository;
    }

    public List<Route> findAll() {
        return repository.findAll();
    }

    public List<Route> getByAuthorId(Long authorId) {
        return repository.findByAuthor(accountRepository
                .findById(authorId).get());
    }

    public Route findById(Long id) {
        return repository.findById(id).get();
    }

    public Route createRoute(Route route) {
        route.setId(0L);
        return repository.save(route);
    }

    public  Route update(Route route) {
        if (route.getId() == null) {
            throw new IllegalArgumentException();
        }
        return repository.save(route);
    }

    public void deleteById(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}
