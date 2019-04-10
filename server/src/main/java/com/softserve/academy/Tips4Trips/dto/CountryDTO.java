package com.softserve.academy.Tips4Trips.dto;

import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.Position;

import java.util.List;

public class CountryDTO {

    private Long id;
    private String name;
    private PositionDTO position;
    private List<CityDTO> listOfCities;

    public CountryDTO() {
    }

    public CountryDTO(Long id, String name, PositionDTO position, List<CityDTO> listOfCities) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.listOfCities = listOfCities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
        this.position = position;
    }

    public List<CityDTO> getListOfCities() {
        return listOfCities;
    }

    public void setListOfCities(List<CityDTO> listOfCities) {
        this.listOfCities = listOfCities;
    }
}
