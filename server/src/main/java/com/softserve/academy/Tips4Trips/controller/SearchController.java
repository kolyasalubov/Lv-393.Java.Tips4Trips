package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.RouteConverter;
import com.softserve.academy.Tips4Trips.dto.info.RouteInfoDTO;
import com.softserve.academy.Tips4Trips.dto.search.RouteSearchParams;
import com.softserve.academy.Tips4Trips.service.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/search")
public class SearchController {

    private SearchService service;
    private RouteConverter routeConverter;

    public SearchController(SearchService service, RouteConverter routeConverter) {
        this.service = service;
        this.routeConverter = routeConverter;
    }

    @PostMapping("/routes")
    public ResponseEntity<List<RouteInfoDTO>> findRoutesByParams(@RequestBody RouteSearchParams params) {
        return new ResponseEntity<>(routeConverter
                .convertToInfoDTO(service.findRoutesByParams(params)), HttpStatus.OK);
    }
}
