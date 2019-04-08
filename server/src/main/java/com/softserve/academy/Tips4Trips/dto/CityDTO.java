package com.softserve.academy.Tips4Trips.dto;

import com.softserve.academy.Tips4Trips.entity.Country;
import com.softserve.academy.Tips4Trips.entity.Position;
import com.softserve.academy.Tips4Trips.entity.place.Place;

import java.util.List;

public class CityDTO {

    private Long id;
    private String name;
    private PositionDTO position;
    private CountryDTO country;
    private List<PlaceDTO> listOfPlaces;

    public CityDTO() {
    }

    public CityDTO(Long id, String name, PositionDTO position, CountryDTO country, List<PlaceDTO> listOfPlaces) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.country = country;
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

    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
        this.position = position;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    public List<PlaceDTO> getListOfPlaces() {
        return listOfPlaces;
    }

    public void setListOfPlaces(List<PlaceDTO> listOfPlaces) {
        this.listOfPlaces = listOfPlaces;
    }
}
