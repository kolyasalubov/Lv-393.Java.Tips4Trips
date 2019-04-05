package com.softserve.academy.Tips4Trips.entity.place;

import com.softserve.academy.Tips4Trips.entity.Position;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant extends Building {

    @Column(name = "average_bill")
    private Float averageBill;

    @Column(name = "has_vegan_food")
    private Boolean hasVeganFood;

    public Restaurant() {
        super();
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