package com.softserve.academy.Tips4Trips.dto;

import com.softserve.academy.Tips4Trips.entity.enums.TypeOfBuilding;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

public class BuildingDTO extends PlaceDTO {
    private List<DayOfWeek> workingDays;
    private String webSite;
    private String telephone;
    private TypeOfBuilding type;
    private Date openingTime;
    private Date closingTime;

    public List<DayOfWeek> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(List<DayOfWeek> workingDays) {
        this.workingDays = workingDays;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public TypeOfBuilding getType() {
        return type;
    }

    public void setType(TypeOfBuilding type) {
        this.type = type;
    }

    public Date getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Date openingTime) {
        this.openingTime = openingTime;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }
}
