package com.softserve.academy.Tips4Trips.entity.place;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "monument")
public class Monument extends Place{

    public Monument() {
        super();
    }

    public Monument(@Size(max = 35) @NotBlank String name, String description,
                    @Size(max = 60) @NotBlank String address,
                    @NotNull Position position, @Size(max = 60) String photoPath) {
        super(name, description, address, position, photoPath);
    }
}