package com.softserve.academy.Tips4Trips.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "photo_path", length = 500)
    private String photoPath;

    @NotNull
    @Column(name = "author")
    private Account author;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date creationDate;

    @NotNull
    @Column(name = "list_of_places")
    private List<Place> listOfPlaces = new ArrayList<>();

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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
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
