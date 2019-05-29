package com.softserve.academy.Tips4Trips.dto.search;

import java.util.Date;

public class TripSearchParams {
    private String name;
    private String routeName;
    private Date startDate;
    private Date endDate;
    private long minSubscribersCount = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public long getMinSubscribersCount() {
        return minSubscribersCount;
    }

    public void setMinSubscribersCount(long minSubscribersCount) {
        this.minSubscribersCount = minSubscribersCount;
    }
}
