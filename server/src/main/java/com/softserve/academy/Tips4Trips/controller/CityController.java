package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.CityDTO;
import com.softserve.academy.Tips4Trips.dto.CityFeedbackDTO;
import com.softserve.academy.Tips4Trips.dto.CityRatingDTO;
import com.softserve.academy.Tips4Trips.dto.converter.CityConverter;
import com.softserve.academy.Tips4Trips.dto.converter.CityFeedbackConverter;
import com.softserve.academy.Tips4Trips.dto.converter.PlaceConverter;
import com.softserve.academy.Tips4Trips.dto.info.PlaceInfoDTO;
import com.softserve.academy.Tips4Trips.entity.city.City;
import com.softserve.academy.Tips4Trips.entity.city.CityFeedback;
import com.softserve.academy.Tips4Trips.service.CityFeedbackService;
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
@RequestMapping("/cities")
public class CityController {

    private static final Logger logger = Logger.getLogger(CityController.class);

    private CityConverter cityConverter;
    private CityService cityService;
    private PlaceConverter placeConverter;
    private PlaceService placeService;
    private CityFeedbackService cityFeedbackService;
    private CityFeedbackConverter cityFeedbackConverter;

    @Autowired
    public CityController(CityConverter cityConverter,
                          CityService cityService,
                          PlaceConverter placeConverter,
                          PlaceService placeService,
                          CityFeedbackService cityFeedbackService,
                          CityFeedbackConverter cityFeedbackConverter) {
        this.cityFeedbackConverter = cityFeedbackConverter;
        this.cityConverter = cityConverter;
        this.cityService = cityService;
        this.placeConverter = placeConverter;
        this.placeService = placeService;
        this.cityFeedbackService = cityFeedbackService;
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAll() {
        return new ResponseEntity<>(cityConverter.convertToDTO(cityService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/getAllRating")
    public ResponseEntity<List<CityRatingDTO>> getAllRating() {
        return new ResponseEntity<>(cityConverter.convertToRatingDTO(cityService.findAll()), HttpStatus.OK);
    }

    @PostMapping("/addFeedback")
    public ResponseEntity<CityFeedbackDTO> addFeedback(@RequestBody CityFeedbackDTO cityFeedbackDTO) {
        logger.info("add feedback method executing: ");
        CityFeedback cityFeedback = cityFeedbackConverter.convertToEntity(cityFeedbackDTO);
        return new ResponseEntity<>(cityFeedbackConverter.convertToDTO(cityFeedbackService.createCityFeedback(cityFeedback)), HttpStatus.OK);
    }

    @GetMapping("/getCityRating/{id}")
    public ResponseEntity<Double> getCityRating(@PathVariable Long id) {
        logger.info("get city rating method executing: ");
        return new ResponseEntity<>(cityService.getCityRating(id), HttpStatus.OK);
    }

    @GetMapping("/getFeedbacks/{id}")
    public ResponseEntity<List<CityFeedbackDTO>> getFeedbacks(@PathVariable Long id) {
        logger.info("get city by id method executing: ");
        return new ResponseEntity<>(cityFeedbackConverter.convertToDTO(cityFeedbackService.findByCityFeedback(id)), HttpStatus.OK);
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
