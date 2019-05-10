package com.softserve.academy.Tips4Trips.entity.administration;

import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.Sale;
import com.softserve.academy.Tips4Trips.entity.enums.AuthProvider;
import com.softserve.academy.Tips4Trips.entity.enums.Role;
import com.softserve.academy.Tips4Trips.entity.file.Image;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "accounts")
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
    @Column(name = "phone_number", length = 12, unique = true)
    private String phoneNumber;

    @NotNull
    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "registration_date")
    @Temporal(value = TemporalType.DATE)
    private Date registrationDate;

    @Column(name = "about", length = 254)
    private String about;

    @NotNull
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Column(name = "provider_id", nullable = false)
    private String providerId;

    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Post> post;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "accounts_subscribers",
        joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "subscriber_id", referencedColumnName = "id")})
    private List<Account> subscribers;

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private List<User> users;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.REMOVE)
    private List<Sale> sales;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}