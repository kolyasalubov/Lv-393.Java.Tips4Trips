package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.MonumentDTO;
import com.softserve.academy.Tips4Trips.entity.place.Monument;
import com.softserve.academy.Tips4Trips.service.MonumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MonumentConverter implements Converter<Monument, MonumentDTO> {

    private MonumentService monumentService;

    @Autowired
    public MonumentConverter (MonumentService monumentService) {
        this.monumentService = monumentService;
    }

    @Override
    public MonumentDTO convertToDTO(Monument monument) {
        MonumentDTO monumentDTO = new MonumentDTO();
        monumentDTO.setId(monument.getId());
        monumentDTO.setName(monument.getName());
        monumentDTO.setDescription(monument.getDescription());
        monumentDTO.setAddress(monument.getAddress());
        monumentDTO.setPosition(monument.getPosition());
        monumentDTO.setPhotoPath(monument.getPhotoPath());
        monumentDTO.setCityId(monument.getCity().getId());

        return monumentDTO;
    }

    @Override
    public Monument convertToEntity(MonumentDTO monumentDTO) {
        Monument monument = new Monument();
        monument.setName(monumentDTO.getName());
        monument.setDescription(monumentDTO.getDescription());
        monument.setAddress(monumentDTO.getAddress());
        monument.setPosition(monumentDTO.getPosition());
        monument.setPhotoPath(monumentDTO.getPhotoPath());
        return monument;
    }

}