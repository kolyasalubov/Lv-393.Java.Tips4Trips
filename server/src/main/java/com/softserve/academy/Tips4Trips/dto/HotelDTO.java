package com.softserve.academy.Tips4Trips.dto;

public class HotelDTO extends BuildingDTO {
    private Float maximumPrice;

    private Float minimumPrice;

    public Float getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(Float maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public Float getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(Float minimumPrice) {
        this.minimumPrice = minimumPrice;
    }
}
