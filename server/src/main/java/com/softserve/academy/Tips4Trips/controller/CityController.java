package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.CityDTO;
import com.softserve.academy.Tips4Trips.dto.converter.CityConverter;
import com.softserve.academy.Tips4Trips.dto.converter.PlaceConverter;
import com.softserve.academy.Tips4Trips.dto.info.PlaceInfoDTO;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.Country;
import com.softserve.academy.Tips4Trips.service.CityService;
import com.softserve.academy.Tips4Trips.service.PlaceService;
import org.apache.log4j.Logger;
import com.softserve.academy.Tips4Trips.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cities")
public class CityController {

    private static final Logger logger = Logger.getLogger(CityController.class);

    private CityConverter cityConverter;
    private CityService cityService;
    private PlaceConverter placeConverter;
    private PlaceService placeService;

    @Autowired
    public CityController(CityConverter cityConverter,
                          CityService cityService,
                          PlaceConverter placeConverter,
                          PlaceService placeService) {
        this.cityConverter = cityConverter;
        this.cityService = cityService;
        this.placeConverter = placeConverter;
        this.placeService = placeService;
    }

    @PostMapping("/create")
    public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDTO) {
        logger.info("create city method executing: ");
        City city = cityConverter.convertToEntity(cityDTO);
        return new ResponseEntity<>(cityConverter.convertToDTO(cityService.createCity(city)), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        logger.info("get post by id method executing: ");
        return new ResponseEntity<>(cityService.getCount(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getById(@PathVariable Long id) {
        logger.info("get city by id method executing: ");
        return new ResponseEntity<>(cityConverter.convertToDTO(cityService.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}/places")
    public ResponseEntity<List<PlaceInfoDTO>> getAllByCityId(@PathVariable Long id) {
        logger.info("get all by city id method executing: ");
        City city = cityService.findById(id);
        return new ResponseEntity<>(placeConverter.convertToInfoDTO(placeService
                .findByCity(city)), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CityDTO> update(@RequestBody CityDTO cityDTO) {
        logger.info("update city method executing: ");
        City city = cityConverter.convertToEntity(cityDTO);
        return new ResponseEntity<>(cityConverter.convertToDTO(cityService.update(city)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        logger.info("delete city by id method executing: ");
        cityService.deleteById(id);
    }

}
