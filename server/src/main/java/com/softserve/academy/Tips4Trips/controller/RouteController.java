package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.RouteDTO;
import com.softserve.academy.Tips4Trips.dto.converter.RouteConverter;
import com.softserve.academy.Tips4Trips.dto.details.RouteDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.RouteInfoDTO;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/routes")
public class RouteController {

    private RouteService routeService;
    private RouteConverter routeConverter;

    @Autowired
    public RouteController(RouteService routeService,
                           RouteConverter routeConverter) {
        this.routeService = routeService;
        this.routeConverter = routeConverter;
    }

    @GetMapping
    public ResponseEntity<List<RouteInfoDTO>> getAll() {
        return new ResponseEntity<>(routeConverter.convertToInfoDTO(routeService
                .findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteDetailsDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(routeConverter.
                convertToDTO(routeService.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<RouteInfoDTO>> getByAuthor(@PathVariable Long id) {
        return new ResponseEntity<>(routeConverter.convertToInfoDTO(routeService
                .getByAuthorId(id)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<RouteDetailsDTO> createRoute(@RequestBody RouteDetailsDTO routeDetailsDTO) {
        Route route = routeService.createRoute(routeConverter.convertToEntity(routeDetailsDTO));
        return new ResponseEntity<>(routeConverter
                .convertToDTO(route), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<RouteDetailsDTO> update(@RequestBody RouteDetailsDTO routeDetailsDTO) {
        Route route = routeService.update(routeConverter.convertToEntity(routeDetailsDTO));
        return new ResponseEntity<>(routeConverter
                .convertToDTO(route), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        routeService.deleteById(id);
    }

}
