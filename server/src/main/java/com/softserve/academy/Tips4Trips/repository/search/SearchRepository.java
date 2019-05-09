package com.softserve.academy.Tips4Trips.repository.search;

import com.softserve.academy.Tips4Trips.dto.Page;
import com.softserve.academy.Tips4Trips.dto.search.RouteSearchParams;
import com.softserve.academy.Tips4Trips.entity.Route;

import java.util.List;

public interface SearchRepository<ENTITY, PARAMS> {
    Page<ENTITY> findByParams(PARAMS searchParams, long page, int size);
}
