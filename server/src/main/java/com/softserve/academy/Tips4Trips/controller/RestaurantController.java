package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.RestaurantSearchCriteriaConverter;
import com.softserve.academy.Tips4Trips.dto.search.RestaurantSearchCriteriaDTO;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@CrossOrigin
@RequestMapping("/places/restaurants")
public class RestaurantController {

    private static final Logger logger = Logger.getLogger(RestaurantController.class);

    private final RestaurantService restaurantService;
    private final RestaurantConverter restaurantConverter;
    private final PlaceConverter placeConverter;
    private final RestaurantSearchCriteriaConverter searchCriteriaConverter;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestaurantConverter restaurantConverter,
                                PlaceConverter placeConverter, RestaurantSearchCriteriaConverter searchCriteriaConverter) {
        this.restaurantService = restaurantService;
        this.restaurantConverter = restaurantConverter;
        this.placeConverter = placeConverter;
        this.searchCriteriaConverter = searchCriteriaConverter;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlaceInfoDTO>> getAll(){
        logger.info("get all restaurant method executing: ");
        return new ResponseEntity<>(placeConverter
                .convertToInfoDTO(restaurantService.findAll()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PostMapping("/create")
    public ResponseEntity<RestaurantDetailsDTO> createRestaurant(@Valid @RequestBody RestaurantDetailsDTO restaurantDTO) {
        logger.info("create restaurant method executing: ");
        Restaurant restaurant = restaurantConverter.convertToEntity(restaurantDTO);
        return new ResponseEntity<>(restaurantConverter.convertToDTO(restaurantService.createRestaurant(restaurant)),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDetailsDTO> getById(@PathVariable Long id) {
        logger.info("get restaurant by id method executing: ");
        return new ResponseEntity<>(restaurantConverter.convertToDTO(restaurantService.findById(id)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PutMapping("/update")
    public ResponseEntity<RestaurantDetailsDTO> update(@Valid @RequestBody RestaurantDetailsDTO restaurantDetailsDTO) {
        logger.info("update restaurant method executing: ");
        return new ResponseEntity<>(restaurantConverter.convertToDTO(restaurantService.
                update(restaurantConverter.convertToEntity(restaurantDetailsDTO))), HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<PlaceInfoDTO>> filter(@Valid @RequestBody RestaurantSearchCriteriaDTO searchCriteriaDTO) {
        return new ResponseEntity<>(placeConverter.convertToInfoDTO(restaurantService.
                filter(searchCriteriaConverter.convertToEntity(searchCriteriaDTO))), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        logger.info("delete monument by id method executing: ");
        restaurantService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
