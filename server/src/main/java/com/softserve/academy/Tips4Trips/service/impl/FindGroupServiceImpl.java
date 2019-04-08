package com.softserve.academy.Tips4Trips.service.impl;

import com.softserve.academy.Tips4Trips.dto.FindGroupDTO;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.FindGroup;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.repository.FindGroupRepository;
import com.softserve.academy.Tips4Trips.service.FindGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindGroupServiceImpl extends ServiceImpl<FindGroup, Long, FindGroupRepository>
        implements FindGroupService {

    @Autowired
    public FindGroupServiceImpl(FindGroupRepository repository) {
        super(repository);
    }

    @Override
    public List<FindGroup> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }


    @Override
    public List<FindGroup> findByAuthor(Account author) {
        return repository.findByAuthor(author);
    }

    @Override
    public List<FindGroup> findByRoute(Route route) {
        return repository.findByRoute(route);
    }

    @Override
    public FindGroup createFindGroup(FindGroupDTO postDTO) {
        return null;
    }
}
