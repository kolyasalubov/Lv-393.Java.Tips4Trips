package com.softserve.academy.Tips4Trips.service.impl;

import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.repository.RouteRepository;
import com.softserve.academy.Tips4Trips.service.RouteService;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl extends ServiceImpl<Route, Long, RouteRepository>
        implements RouteService {
}
