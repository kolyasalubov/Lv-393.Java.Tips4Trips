package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.MonumentDTO;
import com.softserve.academy.Tips4Trips.dto.converter.MonumentConverter;
import com.softserve.academy.Tips4Trips.entity.place.Monument;
import com.softserve.academy.Tips4Trips.service.MonumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/monuments")
public class MonumentController {

    private MonumentConverter monumentConverter;
    private MonumentService monumentService;

    @Autowired
    public MonumentController(MonumentConverter monumentConverter, MonumentService monumentService) {
        this.monumentConverter = monumentConverter;
        this.monumentService = monumentService;
    }

    @PostMapping("/create")
    public Monument createMonument(MonumentDTO monumentDTO) {
        Monument monument = monumentConverter.convertToEntity(monumentDTO);
        return monumentService.createMonument(monument);
    }
}
