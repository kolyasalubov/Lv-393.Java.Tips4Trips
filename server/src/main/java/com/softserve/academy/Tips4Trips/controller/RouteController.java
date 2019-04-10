package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.RouteDTO;
import com.softserve.academy.Tips4Trips.dto.converter.RouteConverter;
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

    @GetMapping("/all")
    public ResponseEntity<List<RouteDTO>> getAll() {
        return new ResponseEntity<>(routeConverter.convertToDTO(routeService
                .findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(routeConverter.convertToDTO(routeService
                .findById(id)), HttpStatus.OK);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<RouteDTO>> getByAuthor(@PathVariable Long id) {
        return new ResponseEntity<>(routeConverter.convertToDTO(routeService
                .getByAuthorId(id)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<RouteDTO> createRoute(@RequestBody RouteDTO routeDTO) {
        Route route = routeService.createRoute(routeConverter.convertToEntity(routeDTO));
        return new ResponseEntity<>(routeConverter
                .convertToDTO(route), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<RouteDTO> update(@RequestBody RouteDTO routeDTO) {
        Route route = routeService.update(routeConverter.convertToEntity(routeDTO));
        return new ResponseEntity<>(routeConverter
                .convertToDTO(route), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        routeService.deleteById(id);
    }

}
