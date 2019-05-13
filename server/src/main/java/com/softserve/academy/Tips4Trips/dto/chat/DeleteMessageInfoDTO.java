package com.softserve.academy.Tips4Trips.dto.chat;

public class DeleteMessageInfoDTO {

    private Long messageId;

    private Long senderId;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }
}
