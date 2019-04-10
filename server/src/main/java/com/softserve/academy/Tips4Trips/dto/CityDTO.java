package com.softserve.academy.Tips4Trips.dto;

import java.util.List;

public class CityDTO {

    private Long id;
    private String name;
    private PositionDTO position;
    private Long countryId;

    public CityDTO() {
    }

    public CityDTO(Long id, String name, PositionDTO position, Long countryId, List<PlaceDTO> listOfPlaces) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.countryId = countryId;
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

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}
