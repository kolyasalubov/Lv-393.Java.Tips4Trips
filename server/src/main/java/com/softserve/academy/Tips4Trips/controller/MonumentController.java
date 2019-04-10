package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.MonumentDTO;
import com.softserve.academy.Tips4Trips.dto.converter.MonumentConverter;
import com.softserve.academy.Tips4Trips.entity.place.Monument;
import com.softserve.academy.Tips4Trips.service.MonumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public ResponseEntity<List<MonumentDTO>> getAll(){
        return new ResponseEntity<>(monumentConverter
                .convertToDTO(monumentService.findAll()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public Monument createMonument(@RequestBody MonumentDTO monumentDTO) {
        Monument monument = monumentConverter.convertToEntity(monumentDTO);
        return monumentService.createMonument(monument);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonumentDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(monumentConverter.convertToDTO(monumentService.findById(id)), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Monument> update(@RequestBody MonumentDTO monumentDTO) {
        return new ResponseEntity<>(monumentService.update(monumentConverter.convertToEntity(monumentDTO)), HttpStatus.OK);
    }
}
