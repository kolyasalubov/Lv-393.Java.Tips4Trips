package com.softserve.academy.Tips4Trips.dto.details;

import com.softserve.academy.Tips4Trips.dto.file.ImageDTO;
import com.softserve.academy.Tips4Trips.dto.info.AccountInfoDTO;
import com.softserve.academy.Tips4Trips.entity.enums.Role;
import com.softserve.academy.Tips4Trips.entity.file.Image;

import java.util.Date;

public class AccountDetailsDTO extends AccountInfoDTO {

    private String phoneNumber;

    private String email;

    private Date registrationDate;

    private String about;

    private Role role;
//
//    private ImageDTO image;
//
//    public ImageDTO getImage() {
//        return image;
//    }
//    public void setImage(ImageDTO image) {
//        this.image = image;
//    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
