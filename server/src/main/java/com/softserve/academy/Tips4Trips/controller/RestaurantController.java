package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.PlaceConverter;
import com.softserve.academy.Tips4Trips.dto.details.RestaurantDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.converter.RestaurantConverter;
import com.softserve.academy.Tips4Trips.dto.info.PlaceInfoDTO;
import com.softserve.academy.Tips4Trips.entity.place.Restaurant;
import com.softserve.academy.Tips4Trips.service.CityService;
import com.softserve.academy.Tips4Trips.service.RestaurantService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/countries/{countryId}/cities/{cityId}/places/restaurants")
public class RestaurantController {

    private static final Logger logger = Logger.getLogger(RestaurantController.class);

    private RestaurantService restaurantService;
    private RestaurantConverter restaurantConverter;
    private PlaceConverter placeConverter;
    private CityService cityService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestaurantConverter restaurantConverter, PlaceConverter placeConverter, CityService cityService) {
        this.restaurantService = restaurantService;
        this.restaurantConverter = restaurantConverter;
        this.placeConverter = placeConverter;
        this.cityService = cityService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlaceInfoDTO>> getAll(@PathVariable Long countryId, @PathVariable Long cityId){
        return new ResponseEntity<>(placeConverter
                .convertToInfoDTO(restaurantService.findByCity(cityService.findById(cityId).getName())), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<RestaurantDetailsDTO> createRestaurant(@PathVariable Long countryId, @PathVariable Long cityId, @RequestBody RestaurantDetailsDTO restaurantDTO) {
        Restaurant restaurant = restaurantConverter.convertToEntity(restaurantDTO);
        return new ResponseEntity<>(restaurantConverter.convertToDTO(restaurantService.createRestaurant(restaurant)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDetailsDTO> getById(@PathVariable Long countryId, @PathVariable Long cityId, @PathVariable Long id) {
        return new ResponseEntity<>(restaurantConverter.convertToDTO(restaurantService.findById(id)), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<RestaurantDetailsDTO> update(@PathVariable Long countryId, @PathVariable Long cityId, @RequestBody RestaurantDetailsDTO restaurantDetailsDTO) {
        return new ResponseEntity<>(restaurantConverter.convertToDTO(restaurantService.update(restaurantConverter.convertToEntity(restaurantDetailsDTO))), HttpStatus.OK);
    }
}
