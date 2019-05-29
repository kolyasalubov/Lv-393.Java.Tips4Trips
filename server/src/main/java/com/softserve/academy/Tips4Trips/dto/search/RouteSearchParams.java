package com.softserve.academy.Tips4Trips.dto.search;

import java.util.Date;

public class RouteSearchParams {
    private String name;
    private Date startDate;
    private Date endDate;
    private boolean verifiedOnly = true;

    public boolean isVerifiedOnly() {
        return verifiedOnly;
    }

    public void setVerifiedOnly(boolean verifiedOnly) {
        this.verifiedOnly = verifiedOnly;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
