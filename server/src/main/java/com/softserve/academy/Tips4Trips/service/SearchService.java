package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.Page;
import com.softserve.academy.Tips4Trips.dto.search.PostSearchParams;
import com.softserve.academy.Tips4Trips.dto.search.RouteSearchParams;
import com.softserve.academy.Tips4Trips.dto.search.TripSearchParams;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.Trip;
import com.softserve.academy.Tips4Trips.repository.PostRepository;
import com.softserve.academy.Tips4Trips.repository.RouteRepository;
import com.softserve.academy.Tips4Trips.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    RouteRepository routeRepository;
    PostRepository postRepository;
    TripRepository tripRepository;

    @Autowired
    public SearchService(RouteRepository routeRepository,
                         PostRepository postRepository,
                         TripRepository tripRepository) {
        this.routeRepository = routeRepository;
        this.postRepository = postRepository;
        this.tripRepository = tripRepository;
    }

    public Page<Route> findRoutesByParams(RouteSearchParams routeSearchParams, long page, int size) {
        return routeRepository.findByParams(routeSearchParams, page, size);
    }

    public Page<Post> findPostsByParams(PostSearchParams postSearchParams, long page, int size) {
        return postRepository.findByParams(postSearchParams, page, size);
    }

    public Page<Trip> findTripsByParams(TripSearchParams tripSearchParams, long page, int size) {
        return tripRepository.findByParams(tripSearchParams, page, size);
    }
}
