package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.MonumentConverter;
import com.softserve.academy.Tips4Trips.dto.converter.PlaceConverter;
import com.softserve.academy.Tips4Trips.dto.details.MonumentDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.PlaceInfoDTO;
import com.softserve.academy.Tips4Trips.entity.place.Monument;
import com.softserve.academy.Tips4Trips.entity.place.Place;
import com.softserve.academy.Tips4Trips.service.MonumentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/countries/{countryId}/cities/{cityId}/places/monuments")
public class MonumentController {

    private static final Logger logger = Logger.getLogger(MonumentController.class);

    private MonumentConverter monumentConverter;
    private MonumentService monumentService;
    private PlaceConverter placeConverter;

    @Autowired
    public MonumentController(MonumentConverter monumentConverter, MonumentService monumentService, PlaceConverter placeConverter) {
        this.monumentConverter = monumentConverter;
        this.monumentService = monumentService;
        this.placeConverter = placeConverter;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlaceInfoDTO>> getAll(){
        return new ResponseEntity<>(placeConverter
                .convertToInfoDTO(monumentService.findAll()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<MonumentDetailsDTO> createMonument(@RequestBody MonumentDetailsDTO monumentDetailsDTO) {
        Monument monument = monumentConverter.convertToEntity(monumentDetailsDTO);
        return new ResponseEntity<>(monumentConverter.convertToDTO(monumentService.createMonument(monument)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonumentDetailsDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(monumentConverter.convertToDTO(monumentService.findById(id)), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<MonumentDetailsDTO> update(@RequestBody MonumentDetailsDTO monumentDetailsDTO) {
        return new ResponseEntity<>(monumentConverter.convertToDTO(monumentService.update(monumentConverter.convertToEntity(monumentDetailsDTO))), HttpStatus.OK);
    }
}
