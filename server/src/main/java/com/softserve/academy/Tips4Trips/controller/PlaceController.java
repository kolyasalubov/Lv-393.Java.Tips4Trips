package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.PlaceConverter;
import com.softserve.academy.Tips4Trips.dto.info.PlaceInfoDTO;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.service.CityService;
import com.softserve.academy.Tips4Trips.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/countries/{countryId}/cities/{cityId}/places")
public class PlaceController {
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

    @GetMapping
    public ResponseEntity<List<PlaceInfoDTO>> getAllByCityId(@PathVariable Long cityId) {
        City city = cityService.findById(cityId);
        return new ResponseEntity<>(placeConverter.convertToInfoDTO(placeService
                .findByCity(city)), HttpStatus.OK);
    }
}
