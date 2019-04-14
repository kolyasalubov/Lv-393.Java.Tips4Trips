package com.softserve.academy.Tips4Trips.dto;

import com.softserve.academy.Tips4Trips.dto.info.PlaceInfoDTO;
import com.softserve.academy.Tips4Trips.entity.Position;

import java.util.List;

public class CityDTO {

    private Long id;
    private String name;
    private Position position;
    private Long countryId;
    private String places;

    public CityDTO() {
    }

    public CityDTO(Long id, String name, Position position, Long countryId, String places) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.countryId = countryId;
        this.places = places;
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

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }
}
