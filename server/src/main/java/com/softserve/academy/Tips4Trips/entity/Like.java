package com.softserve.academy.Tips4Trips.entity;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "liked_by", referencedColumnName = "id" ,nullable = false)
    private Account likedBy;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id" ,nullable = false)
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
