package com.softserve.academy.Tips4Trips.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name", length = 25, nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", length = 25, nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "phone_number", length = 12, nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "registration_date")
    @Temporal(value = TemporalType.DATE)
    private Date registrationDate;

    @Column(name = "about", length = 254)
    private String about;

    @NotNull
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "author")
    private List<Post> post;

    @OneToMany(mappedBy = "id")
    private List<Account> subscribers;

    @OneToMany(mappedBy = "account")
    private List<User> users;

    @OneToMany(mappedBy = "writtenBy")
    private List<Sale> sales;

    @OneToMany(mappedBy = "likedBy")
    private List<Like> likes;

    @OneToMany(mappedBy = "likedBy")
    private List<Comment> comments;

    public Account() {
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    public List<Account> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Account> subscribers) {
        this.subscribers = subscribers;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
