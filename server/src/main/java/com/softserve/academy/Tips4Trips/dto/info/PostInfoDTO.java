package com.softserve.academy.Tips4Trips.dto.info;

import com.softserve.academy.Tips4Trips.dto.info.AccountInfoDTO;

import java.util.Date;

public class PostInfoDTO {

    private Long id;
    private String name;
    private Date creationDate;
    private String description;
    private AccountInfoDTO authorInfo;
    private long countOfLikes;
    private String self;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCountOfLikes() {
        return countOfLikes;
    }

    public void setCountOfLikes(long countOfLikes) {
        this.countOfLikes = countOfLikes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccountInfoDTO getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(AccountInfoDTO authorInfo) {
        this.authorInfo = authorInfo;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }
}
