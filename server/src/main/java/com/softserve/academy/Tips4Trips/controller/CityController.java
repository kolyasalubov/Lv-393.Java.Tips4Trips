package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.CityDTO;
import com.softserve.academy.Tips4Trips.dto.CountryDTO;
import com.softserve.academy.Tips4Trips.dto.PostDTO;
import com.softserve.academy.Tips4Trips.dto.converter.CityConverter;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.Country;
import com.softserve.academy.Tips4Trips.entity.Post;
import com.softserve.academy.Tips4Trips.service.CityService;
import org.hibernate.HibernateException;
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
        return new ResponseEntity<>(cityConverter
                .convert(cityService.findAll()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDTO) {
        try {
            City city = cityService.createCity(cityDTO);
            return new ResponseEntity<>(cityConverter.convert(city), HttpStatus.CREATED);
        } catch (HibernateException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
