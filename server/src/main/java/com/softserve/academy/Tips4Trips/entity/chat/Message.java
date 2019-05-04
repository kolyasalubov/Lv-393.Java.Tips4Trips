package com.softserve.academy.Tips4Trips.entity.chat;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String messageContent;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime sendTime;

//    @UpdateTimestamp
//    private LocalDateTime lastEditTime;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Account sender;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Chat chat;

    public Message( ) { }

    public Message(String messageContent, LocalDateTime sendTime, Account sender, Chat chat) {
        this.messageContent = messageContent;
        this.sendTime = sendTime;
        this.sender = sender;
        this.chat = chat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
