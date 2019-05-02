package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.NotificationDTO;
import com.softserve.academy.Tips4Trips.service.NotificationService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/notification")
public class NotificationController {

    private static final Logger logger = Logger.getLogger(NotificationController.class);

    private NotificationService notificService;
    private ModelMapper modelMapper;

    @Autowired
    public NotificationController(NotificationService notificService, ModelMapper modelMapper) {
        this.notificService = notificService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/comment/{id}/{page}")
    public ResponseEntity<Page<NotificationDTO>> getComment(@PathVariable Long id, @PathVariable int page) {
        logger.info("get comment by id method executing: ");
        Page<NotificationDTO> commentPage=notificService.getCommentsByCreaterId(id,page);
        return new ResponseEntity<>(commentPage, HttpStatus.OK);
    }

    @GetMapping("/like/{id}/{page}")
    public ResponseEntity<Page<NotificationDTO>> getLike(@PathVariable Long id, @PathVariable int page) {
        logger.info("get comment by id method executing: ");
        Page<NotificationDTO> commentPage=notificService.getLikeByCreaterId(id,page);
        return new ResponseEntity<>(commentPage, HttpStatus.OK);
    }

}
