package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.PostConverter;
import com.softserve.academy.Tips4Trips.dto.converter.RouteConverter;
import com.softserve.academy.Tips4Trips.dto.converter.TripConverter;
import com.softserve.academy.Tips4Trips.dto.info.PostInfoDTO;
import com.softserve.academy.Tips4Trips.dto.info.RouteInfoDTO;
import com.softserve.academy.Tips4Trips.dto.info.TripInfoDTO;
import com.softserve.academy.Tips4Trips.dto.search.PostSearchParams;
import com.softserve.academy.Tips4Trips.dto.search.RouteSearchParams;
import com.softserve.academy.Tips4Trips.dto.search.TripSearchParams;
import com.softserve.academy.Tips4Trips.service.SearchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/search")
public class SearchController {

    private SearchService service;
    private RouteConverter routeConverter;
    private ModelMapper modelMapper;
    private TripConverter tripConverter;

    @Autowired
    public SearchController(SearchService service,
                            RouteConverter routeConverter,
                            TripConverter tripConverter,
                            ModelMapper modelMapper) {
        this.service = service;
        this.routeConverter = routeConverter;
        this.modelMapper = modelMapper;
        this.tripConverter = tripConverter;
    }

    @PostMapping("/routes")
    public ResponseEntity<List<RouteInfoDTO>> findRoutesByParams(@RequestBody RouteSearchParams params) {
        return new ResponseEntity<>(routeConverter
                .convertToInfoDTO(service.findRoutesByParams(params)), HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<List<PostInfoDTO>> findPostsByParams(@RequestBody PostSearchParams params) {
        return new ResponseEntity<>(service.findPostsByParams(params).stream()
                .map(post -> modelMapper.map(post, PostInfoDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/trips")
    public ResponseEntity<List<TripInfoDTO>> findRoutesByParams(@RequestBody TripSearchParams params) {
        return new ResponseEntity<>(tripConverter
                .convertToInfoDTO(service.findTripsByParams(params)), HttpStatus.OK);
    }
}
