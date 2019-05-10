package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.Page;
import com.softserve.academy.Tips4Trips.dto.converter.PostConverter;
import com.softserve.academy.Tips4Trips.dto.converter.RouteConverter;
import com.softserve.academy.Tips4Trips.dto.converter.TripConverter;
import com.softserve.academy.Tips4Trips.dto.info.PostInfoDTO;
import com.softserve.academy.Tips4Trips.dto.info.RouteInfoDTO;
import com.softserve.academy.Tips4Trips.dto.info.TripInfoDTO;
import com.softserve.academy.Tips4Trips.dto.search.PostSearchParams;
import com.softserve.academy.Tips4Trips.dto.search.RouteSearchParams;
import com.softserve.academy.Tips4Trips.dto.search.TripSearchParams;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.service.SearchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/search")
public class SearchController {

    public final int DEFAULT_PAGE_SIZE = 10;
    private SearchService service;
    private RouteConverter routeConverter;
    private TripConverter tripConverter;
    private PostConverter postConverter;

    @Autowired
    public SearchController(SearchService service,
                            RouteConverter routeConverter,
                            TripConverter tripConverter,
                            PostConverter postConverter) {
        this.service = service;
        this.routeConverter = routeConverter;
        this.tripConverter = tripConverter;
        this.postConverter = postConverter;
    }

    @PostMapping("/routes")
    public ResponseEntity<Page<RouteInfoDTO>> findRoutesByParams(
            @RequestBody RouteSearchParams params, @RequestParam long page,
            @RequestParam(value = "size", required = false) Integer size) {
        if (size == null || size <= 0) size = DEFAULT_PAGE_SIZE;
        return new ResponseEntity<>(routeConverter
                .convertToInfoDTO(service.findRoutesByParams(params, page, size)), HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<Page<PostInfoDTO>> findPostsByParams(
            @RequestBody PostSearchParams params, @RequestParam long page,
            @RequestParam(value = "size", required = false) Integer size) {
        if (size == null || size <= 0) size = DEFAULT_PAGE_SIZE;
        return new ResponseEntity<>(postConverter
                .convertToInfoDTO(service.findPostsByParams(params, page, size)), HttpStatus.OK);
    }

    @PostMapping("/trips")
    public ResponseEntity<Page<TripInfoDTO>> findTripsByParams(
            @RequestBody TripSearchParams params, @RequestParam long page,
            @RequestParam(value = "size", required = false) Integer size) {
        if (size == null || size <= 0) size = DEFAULT_PAGE_SIZE;
        return new ResponseEntity<>(tripConverter
                .convertToInfoDTO(service.findTripsByParams(params, page, size)), HttpStatus.OK);
    }
}
