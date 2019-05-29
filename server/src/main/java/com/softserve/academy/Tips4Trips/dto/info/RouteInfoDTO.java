package com.softserve.academy.Tips4Trips.dto.info;

import com.softserve.academy.Tips4Trips.dto.MiniPlaceDTO;

import java.util.Date;

public class RouteInfoDTO {
    private Long id;
    private String name;
    private AccountInfoDTO authorInfo;
    private Date creationDate;
    private MiniPlaceDTO begin;
    private MiniPlaceDTO end;
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

    public MiniPlaceDTO getBegin() {
        return begin;
    }

    public void setBegin(MiniPlaceDTO begin) {
        this.begin = begin;
    }

    public MiniPlaceDTO getEnd() {
        return end;
    }

    public void setEnd(MiniPlaceDTO end) {
        this.end = end;
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
