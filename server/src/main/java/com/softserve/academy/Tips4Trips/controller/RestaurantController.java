package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.RestaurantDTO;
import com.softserve.academy.Tips4Trips.dto.converter.RestaurantConverter;
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

    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestaurantConverter restaurantConverter) {
        this.restaurantService = restaurantService;
        this.restaurantConverter = restaurantConverter;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RestaurantDTO>> getAll(){
        return new ResponseEntity<>(restaurantConverter.convertToDTO(restaurantService.findAll()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public Restaurant createRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantConverter.convertToEntity(restaurantDTO);
        return restaurantService.createRestaurant(restaurant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(restaurantConverter.convertToDTO(restaurantService.findById(id)), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Restaurant> update(@RequestBody RestaurantDTO restaurantDTO) {
        return new ResponseEntity<>(restaurantService.update(restaurantConverter.convertToEntity(restaurantDTO)), HttpStatus.OK);
    }
}
