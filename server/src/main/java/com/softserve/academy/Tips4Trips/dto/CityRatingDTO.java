package com.softserve.academy.Tips4Trips.dto;

import com.softserve.academy.Tips4Trips.entity.Position;

public class CityRatingDTO extends CityDTO {

    private Double averageRating;

    public CityRatingDTO() {
    }

    public CityRatingDTO(Double averageRating) {
        this.averageRating = averageRating;
    }

    public CityRatingDTO(CityDTO city) {
        super(city.getId(), city.getName(), city.getPosition(), city.getCountryId(), city.getPlaces());
    }

    public CityRatingDTO(Long id, String name, Position position, Long countryId, String places, Double averageRating) {
        super(id, name, position, countryId, places);
        this.averageRating = averageRating;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
