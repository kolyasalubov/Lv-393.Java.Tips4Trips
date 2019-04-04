package com.softserve.academy.Tips4Trips.entity.place;

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
@Table(name = "hotel")
public class Hotel extends Building {

    @Column(name = "maximum_price")
    private Float maximumPrice;

    @Column(name = "minimum_price")
    private Float minimumPrice;

    public Hotel() {
        super();
    }

    public Hotel(@Size(max = 35) @NotBlank String name, String description,
                 @Size(max = 60) @NotBlank String address,
                 @NotNull Position position, @Size(max = 60) String photoPath,
                 List<DayOfWeek> workingDays, @Size(max = 30) String webSite,
                 @Size(max = 15) String telephone, @NotNull TypeOfBuilding type,
                 Date openingTime, Date closingTime, Float maximumPrice,
                 Float minimumPrice) {
        super(name, description, address, position, photoPath, workingDays,
                webSite, telephone, type, openingTime, closingTime);
        this.maximumPrice = maximumPrice;
        this.minimumPrice = minimumPrice;
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