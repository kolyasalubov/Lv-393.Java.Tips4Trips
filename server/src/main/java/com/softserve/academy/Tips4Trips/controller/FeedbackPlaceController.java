package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.FeedbackPlaceDTO;
import com.softserve.academy.Tips4Trips.dto.PlaceDTO;
import com.softserve.academy.Tips4Trips.dto.converter.PlaceConverter;
import com.softserve.academy.Tips4Trips.dto.details.PostDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.details.TripDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.PlaceInfoDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.Trip;
import com.softserve.academy.Tips4Trips.entity.feedback.FeedbackPlace;
import com.softserve.academy.Tips4Trips.entity.place.Place;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.FeedbackPlaceService;
import com.softserve.academy.Tips4Trips.service.PlaceService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/feedback")
public class FeedbackPlaceController {

    private static final Logger logger = Logger.getLogger(FeedbackPlaceController.class);
    private FeedbackPlaceService service;
    private AccountService accountService;
    private PlaceService placeService;
    private ModelMapper modelMapper;
    private PlaceConverter pConverter;

    @Autowired
    public FeedbackPlaceController(FeedbackPlaceService service, AccountService accountService, PlaceService placeService, ModelMapper modelMapper, PlaceConverter pConverter) {
        this.service = service;
        this.accountService = accountService;
        this.placeService = placeService;
        this.modelMapper = modelMapper;
        this.pConverter = pConverter;
    }

    @PostMapping("/create")
    public ResponseEntity<FeedbackPlaceDTO> create(@RequestBody FeedbackPlaceDTO feedbackPlaceDTO) {
        logger.info("create feedback method executing: ");
        return new ResponseEntity<>(modelMapper.map(service.createFeedbackPlace(toEn(feedbackPlaceDTO)),
                FeedbackPlaceDTO.class), HttpStatus.OK);
    }

    @GetMapping("/{id}/{page}")
    public Page<FeedbackPlaceDTO> getByPlace(@PathVariable("id") Long id, @PathVariable("page") int page) {
        logger.info("create feedback method executing: ");
        PageRequest pageable = PageRequest.of(page - 1, 6);
        Page<FeedbackPlace> feedbackPlaces = service.getByPalce(id, pageable);
        Page<FeedbackPlaceDTO> feedbackPlaceDTOPage = feedbackPlaces.map(feedbackPlace -> modelMapper.map(feedbackPlace, FeedbackPlaceDTO.class));
        return feedbackPlaceDTOPage;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        logger.info("delete post by id method executing: ");
        service.deleteFeedbackPlaceById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<FeedbackPlaceDTO> update(@RequestBody FeedbackPlaceDTO feedbackPlaceDTO) {
        logger.info("post update method executing: ");
        return new ResponseEntity<>(modelMapper.map(service.updateFeedbackPlace(toEn(feedbackPlaceDTO)),
                FeedbackPlaceDTO.class), HttpStatus.OK);
    }

    private FeedbackPlace toEn(FeedbackPlaceDTO dto) {
        FeedbackPlace entity = new FeedbackPlace();
        entity.setId(dto.getId());
        entity.setCreator(accountService.findById(dto.getCreator().getId()));
        entity.setPlace(placeService.findById(dto.getPlace().getId()));
        entity.setComment(dto.getComment());
        entity.setMark(dto.getMark());
        return entity;
    }
}
