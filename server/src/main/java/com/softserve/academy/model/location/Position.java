package com.softserve.academy.model.location;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class Position implements Serializable {

    @Column(name="coordinate_x")
    @NotNull
    private Double coordinateX;

    @Column(name="coordinate_y")
    @NotNull
    private Double coordinateY;

    public Position() {}

    public Position(@NotNull Double coordinateX,
                    @NotNull Double coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public Double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(Double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public Double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(Double coordinateY) {
        this.coordinateY = coordinateY;
    }
}
