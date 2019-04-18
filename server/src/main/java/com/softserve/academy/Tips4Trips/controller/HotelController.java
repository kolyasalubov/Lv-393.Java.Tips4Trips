package com.softserve.academy.Tips4Trips.controller;

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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/places/hotels")
public class HotelController {

    private static final Logger logger = Logger.getLogger(HotelController.class);

    private HotelService hotelService;
    private HotelConverter hotelConverter;
    private PlaceConverter placeConverter;

    @Autowired
    public HotelController(HotelService hotelService, HotelConverter hotelConverter, PlaceConverter placeConverter) {
        this.hotelService = hotelService;
        this.hotelConverter = hotelConverter;
        this.placeConverter = placeConverter;
    }




    @GetMapping("/all")
    public ResponseEntity<List<PlaceInfoDTO>> getAll(@PathVariable Long countryId,@PathVariable Long cityId){
        logger.info("get all hotel method executing: ");
        return new ResponseEntity<>(placeConverter.
                convertToInfoDTO(hotelService.findAll()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HotelDetailsDTO> createHotel(@PathVariable Long countryId,@PathVariable Long cityId,@RequestBody HotelDetailsDTO hotelDetailsDTO) {
        logger.info("create hotel method executing: ");
        Hotel hotel = hotelConverter.convertToEntity(hotelDetailsDTO);
        return new ResponseEntity<>(hotelConverter.convertToDTO(hotelService.createHotel(hotel)), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<HotelDetailsDTO> getById(@PathVariable Long countryId,@PathVariable Long cityId,@PathVariable Long id) {
        logger.info("get hotel by id method executing: ");
        return new ResponseEntity<>(hotelConverter.convertToDTO(hotelService.findById(id)), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<HotelDetailsDTO> update(@PathVariable Long countryId,@PathVariable Long cityId,@RequestBody HotelDetailsDTO hotelDetailsDTO) {
        logger.info("update hotel method executing: ");
        return new ResponseEntity<>(hotelConverter.convertToDTO(hotelService.update(hotelConverter.convertToEntity(hotelDetailsDTO))), HttpStatus.OK);
    }
}
