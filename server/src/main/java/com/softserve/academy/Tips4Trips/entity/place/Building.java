package package com.softserve.academy.Tips4Trips.entity.place;

import com.softserve.academy.Tips4Trips.entity.Position;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "building")
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

    public Building(@Size(max = 35) @NotBlank String name, String description,
                    @Size(max = 60) @NotBlank String address,
                    @NotNull Position position, @Size(max = 60) String photoPath,
                    List<DayOfWeek> workingDays, @Size(max = 30) String webSite,
                    @Size(max = 15) String telephone, @NotNull TypeOfBuilding type,
                    Date openingTime, Date closingTime) {
        super(name, description, address, position, photoPath);
        this.workingDays = workingDays;
        this.webSite = webSite;
        this.telephone = telephone;
        this.type = type;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
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