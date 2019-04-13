package com.softserve.academy.Tips4Trips.dto;

import com.softserve.academy.Tips4Trips.entity.Position;

import java.util.List;

public class CountryDTO {

    private Long id;
    private String name;
    private Position position;
    private List<CityDTO> listOfCities;
    private String self;

    public CountryDTO() {
    }

    public CountryDTO(Long id, String name, Position position, List<CityDTO> listOfCities) {
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<CityDTO> getListOfCities() {
        return listOfCities;
    }

    public void setListOfCities(List<CityDTO> listOfCities) {
        this.listOfCities = listOfCities;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getSelf() {
        return self;
    }
}
