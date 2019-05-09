package com.softserve.academy.Tips4Trips.dto.search;

import java.util.Date;

public class RestaurantSearchCriteria {
    private Integer cityId;
    private Date worksAt;
    private Float averageBill;
    private Boolean hasVeganFood;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Date getWorksAt() {
        return worksAt;
    }

    public void setWorksAt(Date worksAt) {
        this.worksAt = worksAt;
    }

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
