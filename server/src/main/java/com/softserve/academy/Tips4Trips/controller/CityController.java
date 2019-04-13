package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.CityDTO;
import com.softserve.academy.Tips4Trips.dto.converter.CityConverter;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.Country;
import com.softserve.academy.Tips4Trips.service.CityService;
import com.softserve.academy.Tips4Trips.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("countries/{countryId}/cities")
public class CityController {
    private CityConverter cityConverter;
    private CityService cityService;
    private CountryService countryService;

    @Autowired
    public CityController(CityConverter cityConverter, CityService cityService,
                          CountryService countryService) {
        this.cityConverter = cityConverter;
        this.cityService = cityService;
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllByCountryId(@PathVariable Long countryId) {
        Country country = countryService.findById(countryId);
        return new ResponseEntity<>(cityConverter.convertToDTO(cityService
                .findByCountry(country)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDTO) {
        City city = cityConverter.convertToEntity(cityDTO);
        return new ResponseEntity<>(cityConverter.convertToDTO(cityService.createCity(city)), HttpStatus.OK);
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<CityDTO> getById(@PathVariable Long cityId) {
        return new ResponseEntity<>(cityConverter.convertToDTO(cityService.findById(cityId)), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CityDTO> update(@RequestBody CityDTO cityDTO) {
        City city = cityConverter.convertToEntity(cityDTO);
        return new ResponseEntity<>(cityConverter.convertToDTO(cityService.update(city)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cityId}")
    public void deleteById(@PathVariable Long cityId) {
        cityService.deleteById(cityId);
    }

}
