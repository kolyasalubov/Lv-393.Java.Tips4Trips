package com.softserve.academy.Tips4Trips.entity.place;

import com.softserve.academy.Tips4Trips.entity.Position;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "buildings")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Building extends Place {

    @Enumerated
    @ElementCollection(targetClass = DayOfWeek.class)
    @NotNull
    @Column(name = "working_day", columnDefinition = "smallint",
            nullable = false)
    private List<DayOfWeek> workingDays;

    @Size(max = 30)
    @Column(length = 30)
    private String webSite;

    @Size(max = 15)
    @Column(length = 15)
    private String telephone;

    @Enumerated
    @NotNull
    @Column(columnDefinition = "smallint", nullable = false)
    private TypeOfBuilding type;

    @Column(name = "opening_time")
    @Temporal(TemporalType.TIME)
    private Date openingTime;

    @Column(name = "closing_time")
    @Temporal(TemporalType.TIME)
    private Date closingTime;

    public Building() {
        super();
    }

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