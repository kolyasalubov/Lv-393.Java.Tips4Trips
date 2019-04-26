package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.FindGroupConverter;
import com.softserve.academy.Tips4Trips.dto.details.TripDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.PostInfoDTO;
import com.softserve.academy.Tips4Trips.dto.info.TripInfoDTO;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.FindGroup;
import com.softserve.academy.Tips4Trips.service.TripService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/trips")
public class TripController {

    private static final Logger logger = Logger.getLogger(TripController.class);

    private TripService tripService;
    private FindGroupConverter findGroupConverter;

    @Autowired
    public TripController(TripService tripService, FindGroupConverter findGroupConverter) {
        this.tripService = tripService;
        this.findGroupConverter = findGroupConverter;
    }

    @GetMapping
    public ResponseEntity<List<TripInfoDTO>> getAll() {
        logger.info("find group get all method executing: ");
        return new ResponseEntity<>(findGroupConverter
                .convertToInfoDTO(tripService.findAll()), HttpStatus.OK);
    }

    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public Page<TripDetailsDTO> listArticlesPageByPage(@PathVariable("page") int page) {
        PageRequest pageable = PageRequest.of(page - 1, 6);
        Page<FindGroup> articlePage = tripService.getPaginatedArticles(pageable);
        Page<TripDetailsDTO> postDetailsDTOS = articlePage.map(trip -> findGroupConverter.convertToDTO(trip));
        return postDetailsDTOS;

    }

    @GetMapping("/{id}")
    public ResponseEntity<TripDetailsDTO> getById(@PathVariable Long id) {
        logger.info("find group get by id method executing: ");
        FindGroup findGroup = tripService.findById(id);
        if (findGroup == null) {
            return null;
        }
        return new ResponseEntity<>(findGroupConverter
                .convertToDTO(findGroup), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<TripDetailsDTO> createPost(@RequestBody TripDetailsDTO findGroupDetailsDTO) {
        logger.info("find group create post method executing: ");
        FindGroup findGroup = tripService.createFindGroup(findGroupConverter.convertToEntity(findGroupDetailsDTO));
        return new ResponseEntity<>(findGroupConverter.convertToDTO(findGroup), HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        logger.info("delete by id method executing: ");
        tripService.deleteById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<TripDetailsDTO> update(@RequestBody TripDetailsDTO findGroupDetailsDTO) {
        logger.info("find group update post method executing:  ");
        FindGroup findGroup = tripService.update(findGroupConverter.convertToEntity(findGroupDetailsDTO));
        return new ResponseEntity<>(findGroupConverter.convertToDTO(findGroup), HttpStatus.CREATED);
    }


}
