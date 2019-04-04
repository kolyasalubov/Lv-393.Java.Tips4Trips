package com.softserve.academy.Tips4Trips.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "commented_by")
    private Account commentedBy;

    @NotNull
    @Column(name = "creation_date")
    @Temporal(value = TemporalType.DATE)
    private Date creationDate;

    @NotNull
    @Column(name = "text", length = 500)
    private String text;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Account getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(Account commentedBy) {
        this.commentedBy = commentedBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
