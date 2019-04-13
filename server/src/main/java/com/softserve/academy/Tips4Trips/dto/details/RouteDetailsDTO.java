package com.softserve.academy.Tips4Trips.dto.details;

import com.softserve.academy.Tips4Trips.dto.PlaceDTO;
import com.softserve.academy.Tips4Trips.dto.info.PlaceInfoDTO;
import com.softserve.academy.Tips4Trips.dto.info.RouteInfoDTO;

import java.util.List;

public class RouteDetailsDTO extends RouteInfoDTO {
    private String photoPath;
    private List<PlaceInfoDTO> places;

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public List<PlaceInfoDTO> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceInfoDTO> places) {
        this.places = places;
    }
}
