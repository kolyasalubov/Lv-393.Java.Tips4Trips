package com.softserve.academy.Tips4Trips.entity;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.file.Image;
import com.softserve.academy.Tips4Trips.entity.place.Place;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "is_verified")
    private boolean verified = false;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private Account author;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date creationDate;

    @ManyToMany
    @JoinTable(name = "routes_places",
            joinColumns = {@JoinColumn(name = "route_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "place_id", referencedColumnName = "id")})
    private List<Place> listOfPlaces;

    public Route() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Place> getListOfPlaces() {
        return listOfPlaces;
    }

    public void setListOfPlaces(List<Place> listOfPlaces) {
        this.listOfPlaces = listOfPlaces;
    }
}
