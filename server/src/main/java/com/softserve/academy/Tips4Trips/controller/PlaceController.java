package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.PlaceDTO;
import com.softserve.academy.Tips4Trips.dto.converter.PlaceConverter;
import com.softserve.academy.Tips4Trips.dto.details.AccountDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.PlaceInfoDTO;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.file.Image;
import com.softserve.academy.Tips4Trips.entity.place.Place;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;
import com.softserve.academy.Tips4Trips.exception.FileIOException;
import com.softserve.academy.Tips4Trips.service.CityService;
import com.softserve.academy.Tips4Trips.service.PlaceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/places")
public class PlaceController {

    private static final Logger logger = Logger.getLogger(PlaceController.class);

    private PlaceService placeService;
    private PlaceConverter placeConverter;

    @Autowired
    public PlaceController(PlaceService placeService,
                           PlaceConverter placeConverter) {
        this.placeService = placeService;
        this.placeConverter = placeConverter;
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<PlaceInfoDTO>> getByName(@PathVariable String name) {
        return new ResponseEntity<>(placeConverter.convertToInfoDTO(placeService
                .findByName(name)), HttpStatus.OK);
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<PlaceInfoDTO> addImage(@PathVariable Long id,
            @RequestParam("file") MultipartFile file) throws FileIOException {
        Place updatedPlace = placeService.createImageForAccount(file, id);
        return new ResponseEntity<>(placeConverter
                .convertToDTO(updatedPlace), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/image")
    public RedirectView redirectToImageGet(@PathVariable Long id) {
        Image image = placeService.findById(id).getImage();
        Long imageId = image == null ? -1 : image.getId();
        return new RedirectView("/images/" + imageId);
    }

    @DeleteMapping("/{id}/image")
    public void deleteImageById(@PathVariable Long id) throws FileIOException,
            DataNotFoundException {
        logger.info("delete image account by id method executing: ");
        placeService.deletePlaceImage(id);
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<PlaceInfoDTO> updateImageById(
            @PathVariable Long id, @RequestParam("file") MultipartFile file)
            throws FileIOException, DataNotFoundException {

        logger.info("update image account by id method executing: ");
        Place updatedPlace = placeService.updatePlaceImage(id, file);
        return new ResponseEntity<>(placeConverter
                .convertToDTO(updatedPlace), HttpStatus.ACCEPTED);
    }

}
