package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.HotelDTO;
import com.softserve.academy.Tips4Trips.dto.converter.HotelConverter;
import com.softserve.academy.Tips4Trips.entity.place.Hotel;
import com.softserve.academy.Tips4Trips.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/hotels")
public class HotelController {
    private HotelService hotelService;
    private HotelConverter hotelConverter;

    @Autowired
    public HotelController(HotelService hotelService, HotelConverter hotelConverter) {
        this.hotelService = hotelService;
        this.hotelConverter = hotelConverter;
    }

    @GetMapping("/all")
    public ResponseEntity<List<HotelDTO>> getAll(){
        return new ResponseEntity<>(hotelConverter.convertToDTO(hotelService.findAll()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public Hotel createHotel(@RequestBody HotelDTO hotelDTO) {
        Hotel hotel = hotelConverter.convertToEntity(hotelDTO);
        return hotelService.createHotel(hotel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(hotelConverter.convertToDTO(hotelService.findById(id)), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Hotel> update(@RequestBody HotelDTO hotelDTO) {
        return new ResponseEntity<>(hotelService.update(hotelConverter.convertToEntity(hotelDTO)), HttpStatus.OK);
    }
}
