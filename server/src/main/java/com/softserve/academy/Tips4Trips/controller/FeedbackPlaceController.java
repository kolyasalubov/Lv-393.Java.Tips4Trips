package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.FeedbackPlaceDTO;
import com.softserve.academy.Tips4Trips.dto.converter.FeedbackPlaceConverter;
import com.softserve.academy.Tips4Trips.entity.feedback.FeedbackPlace;
import com.softserve.academy.Tips4Trips.service.FeedbackPlaceService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/feedback")
public class FeedbackPlaceController {

    private static final Logger logger = Logger.getLogger(FeedbackPlaceController.class);
    private FeedbackPlaceService service;
    private ModelMapper modelMapper;
    private FeedbackPlaceConverter feedbackPlaceConverter;

    @Autowired
    public FeedbackPlaceController(FeedbackPlaceService service, ModelMapper modelMapper, FeedbackPlaceConverter feedbackPlaceConverter) {
        this.service = service;
        this.modelMapper = modelMapper;
        this.feedbackPlaceConverter = feedbackPlaceConverter;
    }

    @PostMapping("/create")
    public ResponseEntity<FeedbackPlaceDTO> create(@RequestBody FeedbackPlaceDTO feedbackPlaceDTO) {
        logger.info("create feedback method executing: ");
        return new ResponseEntity<>(modelMapper.map(service.createFeedbackPlace(feedbackPlaceConverter.convertToEntity(feedbackPlaceDTO)),
                FeedbackPlaceDTO.class), HttpStatus.OK);
    }

    @GetMapping("/{id}/{page}")
    public Page<FeedbackPlaceDTO> getByPlace(@PathVariable("id") Long id, @PathVariable("page") int page) {
        logger.info("get page of feedbacks method executing: ");
        if (page < 1) {
            return null;
        }
        Page<FeedbackPlace> feedbackPlaces = service.getByPlace(id, PageRequest.of(page - 1, 6));
        return feedbackPlaces.map(feedbackPlace -> modelMapper.map(feedbackPlace, FeedbackPlaceDTO.class));
    }

    @GetMapping("/{id}/id_user/{user}")
    public ResponseEntity<Boolean> check(@PathVariable("id") Long postId, @PathVariable("user") Long userId) {
        logger.info("create feedback method executing: ");
        if (postId < 1 || userId < 1) {
            return null;
        }
        return new ResponseEntity<>(service.check(postId, userId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteById(@PathVariable Long id) {
        logger.info("delete post by id method executing: ");
        if (id < 1) {
            return;
        }
        service.deleteFeedbackPlaceById(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<FeedbackPlaceDTO> update(@RequestBody FeedbackPlaceDTO feedbackPlaceDTO) {
        logger.info("post update method executing: ");
        return new ResponseEntity<>(modelMapper.map(service.updateFeedbackPlace(
                feedbackPlaceConverter.convertToEntity(feedbackPlaceDTO)),
                FeedbackPlaceDTO.class), HttpStatus.OK);
    }


}
