package com.softserve.academy.Tips4Trips.service.impl;

import com.softserve.academy.Tips4Trips.entity.FindGroup;
import com.softserve.academy.Tips4Trips.repository.FindGroupRepository;
import com.softserve.academy.Tips4Trips.service.FindGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindGroupServiceImpl extends ServiceImpl<FindGroup, Long, FindGroupRepository>
        implements FindGroupService {

    FindGroupRepository repository;

    @Autowired
    public FindGroupServiceImpl(FindGroupRepository repository){super(repository);}

}
