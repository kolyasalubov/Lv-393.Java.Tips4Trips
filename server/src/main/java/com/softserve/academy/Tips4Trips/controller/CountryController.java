package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.CountryDTO;
import com.softserve.academy.Tips4Trips.dto.converter.CountryConverter;
import com.softserve.academy.Tips4Trips.entity.Country;
import com.softserve.academy.Tips4Trips.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/countries")
public class CountryController {

    CountryConverter countryConverter;
    CountryService countryService;

    @Autowired
    public CountryController(CountryConverter countryConverter, CountryService countryService) {
        this.countryConverter = countryConverter;
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAll() {
        return new ResponseEntity<>(countryConverter.convertToDTO(countryService.findAll()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO) {
        Country country = countryConverter.convertToEntity(countryDTO);
        return new ResponseEntity<>(countryConverter.convertToDTO(countryService.createCountry(country)), HttpStatus.OK);
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<CountryDTO> getById(@PathVariable Long countryId) {
        return new ResponseEntity<>(countryConverter.convertToDTO(countryService.findById(countryId)), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CountryDTO> update(@RequestBody CountryDTO countryDTO) {
        Country country = countryConverter.convertToEntity(countryDTO);
        return new ResponseEntity<>(countryConverter.convertToDTO(countryService.update(country)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{countryId}")
    public void deleteById(@PathVariable Long countryId) {
        countryService.deleteById(countryId);
    }

}
