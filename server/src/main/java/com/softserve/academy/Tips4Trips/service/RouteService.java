package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.RouteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RouteService {

    private static final Logger logger = Logger.getLogger(RouteService.class);

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
        Optional<Account> author = accountRepository.findById(authorId);
        if (author.isPresent()) {
            return repository.findByAuthor(author.get());
        } else {
            throw new NoSuchElementException("Author not found!");
        }
    }

    public Route findById(Long id) {
        Optional<Route> route = repository.findById(id);
        if (route.isPresent()) {
            return route.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    public Route createRoute(Route route) {
        route.setCreationDate(new Date());
        route.setId(-1L);
        return repository.save(route);
    }

    public  Route update(Route route) {
        Optional<Route> existingRoute = repository.findById(route.getId());
        if (!existingRoute.isPresent()) {
            throw new NoSuchElementException();
        } else {
            existingRoute.get().setName(route.getName());
            existingRoute.get().setPhotoPath(route.getPhotoPath());
            existingRoute.get().setAuthor(route.getAuthor());
            existingRoute.get().setListOfPlaces(route.getListOfPlaces());
        }
        return repository.save(existingRoute.get());
    }

    public void deleteById(Long id) {
        // delete route from posts...
        repository.findById(id).ifPresent(repository::delete);
    }
}
