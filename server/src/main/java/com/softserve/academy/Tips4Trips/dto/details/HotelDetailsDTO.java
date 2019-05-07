package com.softserve.academy.Tips4Trips.dto.details;

import com.softserve.academy.Tips4Trips.dto.CityDTO;
import com.softserve.academy.Tips4Trips.dto.file.ImageDTO;
import com.softserve.academy.Tips4Trips.dto.info.PlaceInfoDTO;
import com.softserve.academy.Tips4Trips.entity.Position;
import com.softserve.academy.Tips4Trips.entity.enums.TypeOfBuilding;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

public class HotelDetailsDTO extends PlaceInfoDTO {
    private List<DayOfWeek> workingDays;
    private String webSite;
    private String telephone;
    private TypeOfBuilding type;
    private String openingTime;
    private String closingTime;
    private String address;
    private Position position;
    private ImageDTO image;
    private CityDTO cityDTO;
    private Float maximumPrice;
    private Float minimumPrice;

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

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public ImageDTO getImage() {
        return image;
    }

    public void setImage(ImageDTO image) {
        this.image = image;
    }

    public CityDTO getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(CityDTO cityDTO) {
        this.cityDTO = cityDTO;
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
