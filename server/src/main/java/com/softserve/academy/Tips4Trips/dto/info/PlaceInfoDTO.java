package com.softserve.academy.Tips4Trips.dto.info;

import com.softserve.academy.Tips4Trips.dto.file.ImageDTO;
import com.softserve.academy.Tips4Trips.entity.Position;
import com.softserve.academy.Tips4Trips.entity.enums.PlaceCategory;

public class PlaceInfoDTO {
    private Long id;
    private String name;
    private String description;
    private String self;
    private PlaceCategory category;
    private Position position;
    private ImageDTO image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public PlaceCategory getCategory() {
        return category;
    }

    public void setCategory(PlaceCategory category) {
        this.category = category;
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
}
