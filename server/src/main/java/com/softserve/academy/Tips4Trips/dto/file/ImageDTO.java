package com.softserve.academy.Tips4Trips.dto.file;

import com.softserve.academy.Tips4Trips.dto.info.AccountInfoDTO;

import java.util.Date;

public class ImageDTO {
    private Long id;
    private String name;
    private String format;
    private Date uploadDate;
    private AccountInfoDTO creator;

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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public AccountInfoDTO getCreator() {
        return creator;
    }

    public void setCreator(AccountInfoDTO creator) {
        this.creator = creator;
    }
}
