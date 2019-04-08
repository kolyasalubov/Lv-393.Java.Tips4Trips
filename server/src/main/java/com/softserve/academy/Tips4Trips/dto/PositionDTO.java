package com.softserve.academy.Tips4Trips.dto;

public class PositionDTO {

    private Double coordinateX;
    private Double coordinateY;

    public PositionDTO() {
    }

    public PositionDTO(Double coordinateX, Double coordinateY) {
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
