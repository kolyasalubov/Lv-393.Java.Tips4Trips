package com.softserve.academy.Tips4Trips.dto;

import com.softserve.academy.Tips4Trips.entity.Position;

import java.util.List;

public class CityDTO {

    private Long id;
    private String name;
    private Position position;
    private Long countryId;
    private List<PlaceDetailsDTO> listOfPlaces;

    public CityDTO() {
    }
    
    public CityDTO(Long id, String name, Position position, Long countryId, List<PlaceDetailsDTO> listOfPlaces) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.countryId = countryId;
        this.listOfPlaces = listOfPlaces;
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

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public List<PlaceDetailsDTO> getListOfPlaces() {
        return listOfPlaces;
    }

    public void setListOfPlaces(List<PlaceDetailsDTO> listOfPlaces) {
        this.listOfPlaces = listOfPlaces;
    }
}
