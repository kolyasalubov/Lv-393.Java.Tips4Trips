package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.FindGroup;
import com.softserve.academy.Tips4Trips.repository.TripRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    private static final Logger logger = Logger.getLogger(TripService.class);

    TripRepository repository;

    @Autowired
    public TripService(TripRepository repository) {
        this.repository = repository;
    }

    public List<FindGroup> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public List<FindGroup> findByCreator(Account author) {
        return repository.findByCreator(author);
    }

    public FindGroup findById(Long id) {
        Optional<FindGroup> i =repository.findById(id);
        if(i.isPresent()){
            return i.get();
        }
        else {
            return null;
        }
    }

    public void delete(FindGroup findGroup) {
        repository.delete(findGroup);
    }

    public List<FindGroup> findByRoute(Route route) {
        return repository.findByRoute(route);
    }

    public List<FindGroup> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

    public FindGroup createFindGroup(FindGroup findGroup) {
        return repository.save(findGroup);
    }

    public FindGroup update(FindGroup findGroup) {
        return repository.save(findGroup);
    }

    public Page<FindGroup> getPaginatedArticles(Pageable pageable) {
        return repository.findAllByOrderByIdDesc(pageable);
    }
}
