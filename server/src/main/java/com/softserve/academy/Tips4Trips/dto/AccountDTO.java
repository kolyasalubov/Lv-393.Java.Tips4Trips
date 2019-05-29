package com.softserve.academy.Tips4Trips.dto;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Post;

import java.util.List;

public class AccountDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private List<Post> post;

    private List<Account> subscriptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    public List<Account> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Account> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
