package com.softserve.academy.Tips4Trips.dto.info;

import java.util.Date;

public class CommentInfoDTO {
    private Long id;
    private AccountInfoDTO accountInfo;
    private String shortText;
    private Date creationDate;
    private String self;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountInfoDTO getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfoDTO accountInfo) {
        this.accountInfo = accountInfo;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }
}
