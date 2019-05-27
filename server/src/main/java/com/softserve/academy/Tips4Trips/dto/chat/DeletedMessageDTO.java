package com.softserve.academy.Tips4Trips.dto.chat;

public class DeletedMessageDTO {

    private String status;
    private Long messageId;

    public DeletedMessageDTO() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}
