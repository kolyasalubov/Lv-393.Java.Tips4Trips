package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.HotelSearchCriteriaConverter;
import com.softserve.academy.Tips4Trips.dto.search.HotelSearchCriteriaDTO;
import com.softserve.academy.Tips4Trips.dto.converter.HotelConverter;
import com.softserve.academy.Tips4Trips.dto.converter.PlaceConverter;
import com.softserve.academy.Tips4Trips.dto.details.HotelDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.PlaceInfoDTO;
import com.softserve.academy.Tips4Trips.entity.place.Hotel;
import com.softserve.academy.Tips4Trips.service.HotelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@CrossOrigin
@RequestMapping("/places/hotels")
public class HotelController {

    private static final Logger logger = Logger.getLogger(HotelController.class);

    private HotelService hotelService;
    private HotelConverter hotelConverter;
    private PlaceConverter placeConverter;
    private HotelSearchCriteriaConverter searchCriteriaConverter;

    @Autowired
    public HotelController(HotelService hotelService, HotelConverter hotelConverter, PlaceConverter placeConverter,
                           HotelSearchCriteriaConverter searchCriteriaConverter) {
        this.hotelService = hotelService;
        this.hotelConverter = hotelConverter;
        this.placeConverter = placeConverter;
        this.searchCriteriaConverter = searchCriteriaConverter;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlaceInfoDTO>> getAll(@PathVariable Long cityId){
        logger.info("get all hotel method executing: ");
        return new ResponseEntity<>(placeConverter.
                convertToInfoDTO(hotelService.findAll()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HotelDetailsDTO> createHotel(@Valid @RequestBody HotelDetailsDTO hotelDetailsDTO) {
        logger.info("create hotel method executing: ");
        Hotel hotel = hotelConverter.convertToEntity(hotelDetailsDTO);
        return new ResponseEntity<>(hotelConverter.convertToDTO(hotelService.createHotel(hotel)), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<HotelDetailsDTO> getById(@PathVariable Long id) {
        logger.info("get hotel by id method executing: ");
        return new ResponseEntity<>(hotelConverter.convertToDTO(hotelService.findById(id)), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<HotelDetailsDTO> update(@Valid @RequestBody HotelDetailsDTO hotelDetailsDTO) {
        logger.info("update hotel method executing: ");
        return new ResponseEntity<>(hotelConverter.convertToDTO(hotelService.update(hotelConverter.convertToEntity(hotelDetailsDTO))), HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<PlaceInfoDTO>> filter(@Valid @RequestBody HotelSearchCriteriaDTO searchCriteriaDTO) {
        return new ResponseEntity<>(placeConverter.convertToInfoDTO(hotelService.filter(searchCriteriaConverter.convertToEntity(searchCriteriaDTO))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        logger.info("delete monument by id method executing: ");
        hotelService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
