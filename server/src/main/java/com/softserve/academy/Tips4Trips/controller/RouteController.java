package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.RouteConverter;
import com.softserve.academy.Tips4Trips.dto.details.RouteDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.RouteInfoDTO;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.service.RouteService;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/routes")
public class RouteController {

    private static final Logger logger = Logger.getLogger(RouteController.class);

    private RouteService routeService;
    private RouteConverter routeConverter;
    public final int DEFAULT_PAGE_SIZE = 6;

    @Autowired
    public RouteController(RouteService routeService,
                           RouteConverter routeConverter) {
        this.routeService = routeService;
        this.routeConverter = routeConverter;
    }

    @GetMapping
    public ResponseEntity<Page<RouteInfoDTO>> getAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {
        Pageable pageable = getPageable(page, size);
        Page<Route> routePage = routeService.findAll(pageable);
        return new ResponseEntity<>(routePage
                .map(routeConverter::convertToInfoDTO), HttpStatus.OK);
    }


    @GetMapping("/verified")
    public ResponseEntity<Page<RouteInfoDTO>> getVerified(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {
        logger.info("get verified routes method executing: ");
        Pageable pageable = getPageable(page, size);
        Page<Route> routePage = routeService.findByVerified(true, pageable);
        return new ResponseEntity<>(routePage
                .map(routeConverter::convertToInfoDTO), HttpStatus.OK);
    }

    @GetMapping("/notVerified")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<Page<RouteInfoDTO>> getNotVerified(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {
        logger.info("get verified routes method executing: ");
        Pageable pageable = getPageable(page, size);
        Page<Route> routePage = routeService.findByVerified(false, pageable);
        return new ResponseEntity<>(routePage
                .map(routeConverter::convertToInfoDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteDetailsDTO> getById(@PathVariable Long id) {
        logger.info("get route by id method executing: ");
        return new ResponseEntity<>(routeConverter.
                convertToDTO(routeService.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<RouteDetailsDTO> getByName(@PathVariable String name) {
        logger.info("get route by id method executing: ");
        return new ResponseEntity<>(routeConverter.
                convertToDTO(routeService.findByName(name)), HttpStatus.OK);
    }

    @GetMapping("/names/{name}")
    public ResponseEntity<List<String>> getNamesContaining(@PathVariable String name) {
        return new ResponseEntity<>(routeService
                .findDistinctNamesContaining(name), HttpStatus.OK);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<RouteInfoDTO>> getByAuthor(@PathVariable Long id) {
        logger.info("get route by author method executing: ");
        return new ResponseEntity<>(routeConverter.convertToInfoDTO(routeService
                .findByAuthorId(id)), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<RouteDetailsDTO> createRoute(@RequestBody RouteDetailsDTO routeDetailsDTO) {
        logger.info("create route method executing: ");
        Route route = routeService.createRoute(routeConverter.convertToEntity(routeDetailsDTO));
        return new ResponseEntity<>(routeConverter
                .convertToDTO(route), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or @A.mayEditRoute(#routeDetailsDTO.id)")
    public ResponseEntity<RouteDetailsDTO> update(@RequestBody RouteDetailsDTO routeDetailsDTO) {
        logger.info("update route method executing: ");
        Route route = routeService.update(routeConverter.convertToEntity(routeDetailsDTO));
        return new ResponseEntity<>(routeConverter
                .convertToDTO(route), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/verify")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public void verify(@PathVariable Long id) {
        logger.info("verify route method executing: ");
        routeService.verify(id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or @A.mayEditRoute(#id)")
    public void deleteById(@PathVariable Long id) {
        logger.info("delete route by id method executing: ");
        routeService.deleteById(id);
    }

    private Pageable getPageable(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        if (page == null || page < 0) page = 0;
        if (size == null || size <= 0) size = DEFAULT_PAGE_SIZE;
        return PageRequest.of(page, size);
    }

}
