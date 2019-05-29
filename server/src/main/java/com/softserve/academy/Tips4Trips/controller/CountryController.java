package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.CityDTO;
import com.softserve.academy.Tips4Trips.dto.CountryDTO;
import com.softserve.academy.Tips4Trips.dto.converter.CityConverter;
import com.softserve.academy.Tips4Trips.dto.converter.CountryConverter;
import com.softserve.academy.Tips4Trips.entity.Country;
import com.softserve.academy.Tips4Trips.service.CityService;
import com.softserve.academy.Tips4Trips.service.CountryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/countries")
public class CountryController {

    private static final Logger logger = Logger.getLogger(CountryController.class);

    private CountryConverter countryConverter;
    private CountryService countryService;
    private CityConverter cityConverter;
    private CityService cityService;


    @Autowired
    public CountryController(CountryConverter countryConverter,
                             CountryService countryService,
                             CityConverter cityConverter,
                             CityService cityService) {
        this.countryConverter = countryConverter;
        this.countryService = countryService;
        this.cityConverter = cityConverter;
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAll() {
        return new ResponseEntity<>(countryConverter.convertToDTO(countryService.findAll()), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PostMapping("/create")
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO) {
        logger.info("create country method executing: ");
        Country country = countryConverter.convertToEntity(countryDTO);
        return new ResponseEntity<>(countryConverter.convertToDTO(countryService.createCountry(country)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getById(@PathVariable Long id) {
        logger.info("get by id method executing: ");
        return new ResponseEntity<>(countryConverter.convertToDTO(countryService.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/{countryId}/cities")
    public ResponseEntity<List<CityDTO>> getAllByCountryId(@PathVariable Long countryId) {
        logger.info("get all city method executing: ");
        Country country = countryService.findById(countryId);
        return new ResponseEntity<>(cityConverter.convertToDTO(cityService
                .findByCountry(country)), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PutMapping("/update")
    public ResponseEntity<CountryDTO> update(@RequestBody CountryDTO countryDTO) {
        logger.info("update country method executing: ");
        Country country = countryService.update(countryConverter.convertToEntity(countryDTO));
        return new ResponseEntity<>(countryConverter.convertToDTO(countryService.update(country)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        logger.info("delete country by id method executing: ");
        countryService.deleteById(id);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<CountryDTO> findByName(@PathVariable String name) {
        logger.info("get by city name method executing: ");
        return new ResponseEntity<>(countryConverter.convertToDTO(countryService.findByName(name)), HttpStatus.OK);
    }

}
