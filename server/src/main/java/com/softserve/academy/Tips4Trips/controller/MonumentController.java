package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.MonumentConverter;
import com.softserve.academy.Tips4Trips.dto.converter.PlaceConverter;
import com.softserve.academy.Tips4Trips.dto.details.MonumentDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.PlaceInfoDTO;
import com.softserve.academy.Tips4Trips.entity.place.Monument;
import com.softserve.academy.Tips4Trips.entity.place.Place;
import com.softserve.academy.Tips4Trips.service.CityService;
import com.softserve.academy.Tips4Trips.service.MonumentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@CrossOrigin
@RequestMapping("/places/monuments")
public class MonumentController {

    private static final Logger logger = Logger.getLogger(MonumentController.class);

    private MonumentConverter monumentConverter;
    private MonumentService monumentService;
    private PlaceConverter placeConverter;
    private CityService cityService;

    @Autowired
    public MonumentController(MonumentConverter monumentConverter, MonumentService monumentService, PlaceConverter placeConverter, CityService cityService) {
        this.monumentConverter = monumentConverter;
        this.monumentService = monumentService;
        this.placeConverter = placeConverter;
        this.cityService = cityService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlaceInfoDTO>> getAll(){
        logger.info("get all monument method executing: ");
        return new ResponseEntity<>(placeConverter
                .convertToInfoDTO(monumentService.findAll()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<MonumentDetailsDTO> createMonument(@Valid @RequestBody MonumentDetailsDTO monumentDetailsDTO) {
        logger.info("create monument method executing: ");
        Monument monument = monumentConverter.convertToEntity(monumentDetailsDTO);
        return new ResponseEntity<>(monumentConverter.convertToDTO(monumentService.createMonument(monument)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonumentDetailsDTO> getById(@PathVariable Long id) {
        logger.info("get monument by id method executing: ");
        return new ResponseEntity<>(monumentConverter.convertToDTO(monumentService.findById(id)), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<MonumentDetailsDTO> update(@Valid @RequestBody MonumentDetailsDTO monumentDetailsDTO) {
        logger.info("update monument method executing: ");
        return new ResponseEntity<>(monumentConverter.convertToDTO(monumentService.update(monumentConverter.convertToEntity(monumentDetailsDTO))), HttpStatus.OK);
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<List<PlaceInfoDTO>> getAllByCity(@PathVariable Long id) {
        logger.info("get all monuments by city method executing: ");
        return new ResponseEntity<>(placeConverter.convertToInfoDTO(monumentService.findByCity(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        logger.info("delete monument by id method executing: ");
        monumentService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
