package com.softserve.academy.Tips4Trips.dto.chat;

import javax.validation.constraints.Positive;

public class DeleteMessageDTO {
    @Positive
    private Long accountId;

    @Positive
    private Long id;

    private String status;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
