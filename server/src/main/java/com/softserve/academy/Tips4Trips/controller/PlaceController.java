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
@RequestMapping
public class PlaceController {

    private static final Logger logger = Logger.getLogger(PlaceController.class);

    private PlaceService placeService;
    private CityService cityService;
    private PlaceConverter placeConverter;

    @Autowired
    public PlaceController(PlaceService placeService, CityService cityService,
                           PlaceConverter placeConverter) {
        this.placeService = placeService;
        this.cityService = cityService;
        this.placeConverter = placeConverter;
    }

    @GetMapping("/countries/{countryId}/cities/{cityId}/places")
    public ResponseEntity<List<PlaceInfoDTO>> getAllByCityId(@PathVariable Long cityId) {
        logger.info("get all by city id method executing: ");
        City city = cityService.findById(cityId);
        return new ResponseEntity<>(placeConverter.convertToInfoDTO(placeService
                .findByCity(city)), HttpStatus.OK);
    }

    @GetMapping("/places/name/{name}")
    public ResponseEntity<List<PlaceInfoDTO>> getByName(@PathVariable String name) {
        return new ResponseEntity<>(placeConverter.convertToInfoDTO(placeService
                .findByName(name)), HttpStatus.OK);
    }


}
