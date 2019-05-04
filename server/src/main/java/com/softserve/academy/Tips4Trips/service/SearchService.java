package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.search.RouteSearchParams;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    RouteRepository routeRepository;

    @Autowired
    public SearchService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> findRoutesByParams(RouteSearchParams routeSearchParams) {
        return routeRepository.findByParams(routeSearchParams);
    }
}
