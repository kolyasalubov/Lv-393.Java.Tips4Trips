package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.FindGroup;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.repository.FindGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindGroupService {

    FindGroupRepository repository;

    @Autowired
    public FindGroupService(FindGroupRepository repository) {
        this.repository = repository;
    }

    public List<FindGroup> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public List<FindGroup> findByCreator(Account author) {
        return repository.findByCreator(author);
    }

    public FindGroup findById(Long id) {
        return repository.findById(id).get();
    }

    public void delete(FindGroup findGroup) {
        repository.delete(findGroup);
    }

    public List<FindGroup> findByRoute(Route route) {
        return repository.findByRoute(route);
    }


    public void deleteById(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

    public FindGroup createFindGroup(FindGroup findGroup) {
        return repository.save(findGroup);
    }
}
