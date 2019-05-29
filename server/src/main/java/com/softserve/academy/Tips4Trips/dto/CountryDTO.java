package com.softserve.academy.Tips4Trips.dto;

import com.softserve.academy.Tips4Trips.entity.Position;

import java.util.List;

public class CountryDTO {

    private Long id;
    private String name;
    private Position position;
    private String cities;

    public CountryDTO() {
    }

    public CountryDTO(Long id, String name, Position position, String cities) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.cities = cities;
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

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }
}
