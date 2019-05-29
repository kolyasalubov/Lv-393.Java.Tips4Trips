package com.softserve.academy.Tips4Trips.dto.search;

import java.util.Date;

public class HotelSearchCriteria {
    private Integer cityId;
    private Date worksAt;
    private Float priceIn;

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

    public Float getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(Float priceIn) {
        this.priceIn = priceIn;
    }
}
