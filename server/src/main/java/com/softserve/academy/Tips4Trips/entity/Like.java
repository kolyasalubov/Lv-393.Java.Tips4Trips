package com.softserve.academy.Tips4Trips.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "like")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "liked_by")
    private Account likedBy;

    @NotNull
    @Column(name = "post")
    private Post post;

    public Like() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(Account likedBy) {
        this.likedBy = likedBy;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
