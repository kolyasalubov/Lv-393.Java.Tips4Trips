package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.FindGroupDTO;
import com.softserve.academy.Tips4Trips.dto.converter.FindGroupConverter;
import com.softserve.academy.Tips4Trips.dto.details.FindGroupDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.FindGroupInfoDTO;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.FindGroup;
import com.softserve.academy.Tips4Trips.service.FindGroupService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/findgroups")
public class FindGroupController {

    private static final Logger logger = Logger.getLogger(FindGroupController.class);

    private FindGroupService findGroupService;
    private FindGroupConverter findGroupConverter;

    @Autowired
    public FindGroupController(FindGroupService findGroupService,FindGroupConverter findGroupConverter) {
        this.findGroupService = findGroupService;
        this.findGroupConverter = findGroupConverter;
    }


    @GetMapping
    public ResponseEntity<List<FindGroupInfoDTO>> getAll() {
        return new ResponseEntity<>(findGroupConverter
                .convertToInfoDTO(findGroupService.findAll()), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<FindGroupDetailsDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(findGroupConverter
                .convertToDTO(findGroupService.findById(id)), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<FindGroupDetailsDTO> createPost(@RequestBody FindGroupDetailsDTO findGroupDetailsDTO) {
        FindGroup findGroup = findGroupService.createFindGroup(findGroupConverter.convertToEntity(findGroupDetailsDTO));
        return new ResponseEntity<>(findGroupConverter.convertToDTO(findGroup), HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        findGroupService.deleteById(id);
    }


}
