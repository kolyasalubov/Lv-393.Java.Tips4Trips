package com.softserve.academy.Tips4Trips.dto.info;

import java.util.Date;

public class RouteInfoDTO {
    private Long id;
    private String name;
    private AccountInfoDTO authorInfo;
    private Date creationDate;
    private String self;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountInfoDTO getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(AccountInfoDTO authorInfo) {
        this.authorInfo = authorInfo;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
