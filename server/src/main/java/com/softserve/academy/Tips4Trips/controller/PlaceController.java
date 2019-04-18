package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.PlaceConverter;
import com.softserve.academy.Tips4Trips.dto.info.PlaceInfoDTO;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.service.CityService;
import com.softserve.academy.Tips4Trips.service.PlaceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/places")
public class PlaceController {

    private static final Logger logger = Logger.getLogger(PlaceController.class);

    private PlaceService placeService;
    private PlaceConverter placeConverter;

    @Autowired
    public PlaceController(PlaceService placeService,
                           PlaceConverter placeConverter) {
        this.placeService = placeService;
        this.placeConverter = placeConverter;
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<PlaceInfoDTO>> getByName(@PathVariable String name) {
        return new ResponseEntity<>(placeConverter.convertToInfoDTO(placeService
                .findByName(name)), HttpStatus.OK);
    }

}
