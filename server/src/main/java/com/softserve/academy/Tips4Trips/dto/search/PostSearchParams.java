package com.softserve.academy.Tips4Trips.dto.search;

import java.util.Date;

public class PostSearchParams {
    private String name;
    private Date startDate;
    private Date endDate;
    private long minLikesCount;

    public long getMinLikesCount() {
        return minLikesCount;
    }

    public void setMinLikesCount(long minLikesCount) {
        this.minLikesCount = minLikesCount;
    }

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
}
