package com.softserve.academy.Tips4Trips.repository.search;

import com.softserve.academy.Tips4Trips.dto.search.RouteSearchParams;
import com.softserve.academy.Tips4Trips.entity.Route;

import java.util.List;

public interface SearchRepository<ENTITY, PARAMS> {
    List<ENTITY> findByParams(PARAMS searchParams);
}