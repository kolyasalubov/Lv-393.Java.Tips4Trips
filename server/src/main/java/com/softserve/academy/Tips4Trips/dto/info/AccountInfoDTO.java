package com.softserve.academy.Tips4Trips.dto.info;

import java.util.Date;

public class AccountInfoDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String self;

    private Long imageId;
    private String imageName;
    private String imageFormat;
    private Date imageUploadDate;
    private Long imageCreatorId;

    public Long getImageCreatorId() {
        return imageCreatorId;
    }

    public void setImageCreatorId(Long imageCreatorId) {
        this.imageCreatorId = imageCreatorId;
    }
//private ImageDTO image;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public Date getImageUploadDate() {
        return imageUploadDate;
    }

    public void setImageUploadDate(Date imageUploadDate) {
        this.imageUploadDate = imageUploadDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }
}
