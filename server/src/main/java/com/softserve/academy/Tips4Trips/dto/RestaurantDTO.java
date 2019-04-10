package com.softserve.academy.Tips4Trips.dto;

public class RestaurantDTO extends BuildingDTO {
    private Float averageBill;
    private Boolean hasVeganFood;

    public Float getAverageBill() {
        return averageBill;
    }

    public void setAverageBill(Float averageBill) {
        this.averageBill = averageBill;
    }

    public Boolean getHasVeganFood() {
        return hasVeganFood;
    }

    public void setHasVeganFood(Boolean hasVeganFood) {
        this.hasVeganFood = hasVeganFood;
    }
}
