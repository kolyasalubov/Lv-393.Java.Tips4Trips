package com.softserve.academy.Tips4Trips.repository.custom;

import com.softserve.academy.Tips4Trips.dto.search.RouteSearchParams;
import com.softserve.academy.Tips4Trips.entity.Route;

import java.util.List;

public interface RouteRepositoryCustom {
    List<Route> findByParams(RouteSearchParams routeSearchParams);
}
