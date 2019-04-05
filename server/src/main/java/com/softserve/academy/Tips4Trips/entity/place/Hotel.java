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
@Table(name = "hotels")
public class Hotel extends Building {

    @Column(name = "maximum_price")
    private Float maximumPrice;

    @Column(name = "minimum_price")
    private Float minimumPrice;

    public Hotel() {
        super();
    }

    public Float getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(Float maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public Float getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(Float minimumPrice) {
        this.minimumPrice = minimumPrice;
    }
}