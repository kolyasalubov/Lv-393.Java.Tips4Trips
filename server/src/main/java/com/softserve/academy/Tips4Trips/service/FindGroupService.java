package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.FindGroupDTO;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.FindGroup;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.repository.FindGroupRepository;

import java.util.List;

public interface FindGroupService extends Service<FindGroup, Long, FindGroupRepository> {
    List<FindGroup> searchByName(String name);

    List<FindGroup> findByAuthor(Account author);

    List<FindGroup> findByRoute(Route route);

    FindGroup createFindGroup(FindGroupDTO postDTO);
}
