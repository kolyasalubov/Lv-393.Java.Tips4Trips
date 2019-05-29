package com.softserve.academy.Tips4Trips.dto.details;

import com.softserve.academy.Tips4Trips.dto.info.PlaceInfoDTO;
import com.softserve.academy.Tips4Trips.dto.info.RouteInfoDTO;

import java.util.List;

public class RouteDetailsDTO extends RouteInfoDTO {
    private boolean verified;

    private List<PlaceInfoDTO> places;

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public List<PlaceInfoDTO> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceInfoDTO> places) {
        this.places = places;
    }
}
