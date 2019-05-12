package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.enums.Role;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;
import com.softserve.academy.Tips4Trips.exception.InvalidDataException;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.PostRepository;
import com.softserve.academy.Tips4Trips.repository.RouteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouteService {

    private static final Logger logger = Logger.getLogger(RouteService.class);

    private RouteRepository repository;
    private AccountRepository accountRepository;
    private PostRepository postRepository;


    @Autowired
    public RouteService(RouteRepository repository,
                        AccountRepository accountRepository,
                        PostRepository postRepository) {
        this.repository = repository;
        this.accountRepository = accountRepository;
        this.postRepository = postRepository;
    }

    public List<Route> findAll() {
        return repository.findAll();
    }

    public Page<Route> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Route> findByVerified(boolean verified) {
        return repository.findByVerified(verified);
    }

    public Page<Route> findByVerified(boolean verified, Pageable pageable) {
        return repository.findByVerified(verified, pageable);
    }

    public List<String> findDistinctNamesContaining(String name) {
        return repository
                .findTop5ByNameContainingIgnoreCaseOrderByName(name)
                .stream().map(Route::getName).collect(Collectors.toList());
    }

    public List<Route> findByAuthorId(Long authorId) {
        Optional<Account> author = accountRepository.findById(authorId);
        if (author.isPresent()) {
            return repository.findByAuthor(author.get());
        } else {
            throw new DataNotFoundException("Author not found!");
        }
    }

    public Route findById(Long id) {
        Optional<Route> route = repository.findById(id);
        if (route.isPresent()) {
            return route.get();
        } else {
            throw new DataNotFoundException();
        }
    }

    public Route createRoute(Route route) {
        route.setCreationDate(new Date());
        if (route.getListOfPlaces().size() < 2) {
            throw new InvalidDataException();
        }
        route.setVerified(route.getAuthor().getRole().equals(Role.ADMIN)
                || route.getAuthor().getRole().equals(Role.MODERATOR));
        route.setId(-1L);
        return repository.save(route);
    }

    public Route update(Route route) {
        Optional<Route> existingRoute = repository.findById(route.getId());
        if (!existingRoute.isPresent()) {
            throw new DataNotFoundException();
        } else {
            existingRoute.get().setName(route.getName());
            existingRoute.get().setAuthor(route.getAuthor());
            existingRoute.get().setListOfPlaces(route.getListOfPlaces());
        }
        if (route.getListOfPlaces().size() < 2) {
            throw new InvalidDataException();
        }
        return repository.save(existingRoute.get());
    }

    public void verify(Long id) {
        Optional<Route> route = repository.findById(id);
        if (!route.isPresent()) {
            throw new DataNotFoundException();
        } else {
            if (route.get().isVerified()) {
                throw new InvalidDataException();
            } else {
                route.get().setVerified(true);
                repository.save(route.get());
            }
        }
    }

    public void deleteById(Long id) {
        repository.findById(id).ifPresent(route -> {
            postRepository.findByRoute(route).forEach(post -> post.setRoute(null));
            repository.delete(route);
        });
    }

    public Route findByName(String name) {
        Optional<Route> route = repository.findByName(name);
        if (route.isPresent()) {
            return route.get();
        } else {
            throw new DataNotFoundException();
        }
    }

}
