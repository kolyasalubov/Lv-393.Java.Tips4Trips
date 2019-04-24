package com.softserve.academy.Tips4Trips.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    private ModelMapper modelMapper;
    @Autowired

    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


}
