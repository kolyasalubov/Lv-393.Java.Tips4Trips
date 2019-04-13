package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.PlaceConverter;
import com.softserve.academy.Tips4Trips.dto.details.RestaurantDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.converter.RestaurantConverter;
import com.softserve.academy.Tips4Trips.dto.info.PlaceInfoDTO;
import com.softserve.academy.Tips4Trips.entity.place.Restaurant;
import com.softserve.academy.Tips4Trips.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/restaurants")
public class RestaurantController {

    private RestaurantService restaurantService;
    private RestaurantConverter restaurantConverter;
    private PlaceConverter placeConverter;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestaurantConverter restaurantConverter, PlaceConverter placeConverter) {
        this.restaurantService = restaurantService;
        this.restaurantConverter = restaurantConverter;
        this.placeConverter = placeConverter;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlaceInfoDTO>> getAll(){
        return new ResponseEntity<>(placeConverter
                .convertToInfoDTO(restaurantService.findAll()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<RestaurantDetailsDTO> createRestaurant(@RequestBody RestaurantDetailsDTO restaurantDTO) {
        Restaurant restaurant = restaurantConverter.convertToEntity(restaurantDTO);
        return new ResponseEntity<>(restaurantConverter.convertToDTO(restaurantService.createRestaurant(restaurant)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDetailsDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(restaurantConverter.convertToDTO(restaurantService.findById(id)), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<RestaurantDetailsDTO> update(@RequestBody RestaurantDetailsDTO restaurantDetailsDTO) {
        return new ResponseEntity<>(restaurantConverter.convertToDTO(restaurantService.update(restaurantConverter.convertToEntity(restaurantDetailsDTO))), HttpStatus.OK);
    }
}
