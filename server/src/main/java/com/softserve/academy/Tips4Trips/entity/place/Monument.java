package com.softserve.academy.Tips4Trips.entity.place;

import com.softserve.academy.Tips4Trips.entity.Position;
import com.softserve.academy.Tips4Trips.entity.enums.PlaceCategory;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "monuments")
public class Monument extends Place{

    public Monument() {
        super();
    }

    @Override
    public PlaceCategory getCategory() {
        return PlaceCategory.MONUMENTS;
    }
}
