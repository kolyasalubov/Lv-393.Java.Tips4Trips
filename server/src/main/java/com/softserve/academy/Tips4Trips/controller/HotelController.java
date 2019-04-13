package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.HotelConverter;
import com.softserve.academy.Tips4Trips.dto.converter.PlaceConverter;
import com.softserve.academy.Tips4Trips.dto.details.HotelDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.PlaceInfoDTO;
import com.softserve.academy.Tips4Trips.entity.place.Hotel;
import com.softserve.academy.Tips4Trips.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/countries/{countryId}/cities/{cityId}/places/hotels")
public class HotelController {
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
    public ResponseEntity<List<PlaceInfoDTO>> getAll(){
        return new ResponseEntity<>(placeConverter.
                convertToInfoDTO(hotelService.findAll()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HotelDetailsDTO> createRestaurant(@RequestBody HotelDetailsDTO hotelDetailsDTO) {
        Hotel hotel = hotelConverter.convertToEntity(hotelDetailsDTO);
        return new ResponseEntity<>(hotelConverter.convertToDTO(hotelService.createHotel(hotel)), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<HotelDetailsDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(hotelConverter.convertToDTO(hotelService.findById(id)), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<HotelDetailsDTO> update(@RequestBody HotelDetailsDTO hotelDetailsDTO) {
        return new ResponseEntity<>(hotelConverter.convertToDTO(hotelService.update(hotelConverter.convertToEntity(hotelDetailsDTO))), HttpStatus.OK);
    }
}
