package com.softserve.academy.Tips4Trips.dto.search;

public class RestaurantSearchCriteriaDTO {
    private Integer cityId;
    private String worksAt;
    private Float averageBill;
    private Boolean hasVeganFood;

    public Integer getCityId() {
        return cityId;
    }

    public String getWorksAt() {
        return worksAt;
    }

    public Float getAverageBill() {
        return averageBill;
    }

    public Boolean getHasVeganFood() {
        return hasVeganFood;
    }

}
