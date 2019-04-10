package com.softserve.academy.Tips4Trips.entity.blog;

import com.softserve.academy.Tips4Trips.entity.administration.Account;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commented_by", referencedColumnName = "id", nullable = false)
    private Account commentedBy;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date creationDate;

    @NotNull
    @Column(name = "text", length = 500, nullable = false)
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
