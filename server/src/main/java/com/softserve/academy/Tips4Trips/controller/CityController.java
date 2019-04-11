package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.CityDTO;
import com.softserve.academy.Tips4Trips.dto.converter.CityConverter;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cities")
public class CityController {
    CityConverter cityConverter;
    CityService cityService;

    @Autowired
    public CityController(CityConverter cityConverter, CityService cityService) {
        this.cityConverter = cityConverter;
        this.cityService = cityService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CityDTO>> getAll() {
        return new ResponseEntity<>(cityConverter.convertToDTO(cityService.findAll()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDTO) {
        City city = cityConverter.convertToEntity(cityDTO);
        return new ResponseEntity<>(cityConverter.convertToDTO(cityService.createCity(city)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(cityConverter.convertToDTO(cityService.findById(id)), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CityDTO> update(@RequestBody CityDTO cityDTO) {
        City city = cityConverter.convertToEntity(cityDTO);
        return new ResponseEntity<>(cityConverter.convertToDTO(cityService.update(city)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        cityService.deleteById(id);
    }

}
