package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.FindGroupDTO;
import com.softserve.academy.Tips4Trips.dto.converter.FindGroupConverter;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.FindGroup;
import com.softserve.academy.Tips4Trips.service.FindGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/findgroups")
public class FindGroupController {

    private FindGroupService findGroupService;
    private FindGroupConverter findGroupConverter;

    @Autowired
    public FindGroupController(FindGroupService findGroupService,FindGroupConverter findGroupConverter) {
        this.findGroupService = findGroupService;
        this.findGroupConverter = findGroupConverter;
    }


    @PostMapping("/create")
    public ResponseEntity<FindGroupDTO> createPost(@RequestBody FindGroupDTO findGroupDTO) {
        FindGroup findGroup = findGroupService.createFindGroup(findGroupConverter.convertToEntity(findGroupDTO));
        return new ResponseEntity<>(findGroupConverter.convertToDTO(findGroup), HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        findGroupService.deleteById(id);
    }


}
