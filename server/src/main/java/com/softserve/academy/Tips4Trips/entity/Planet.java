package com.softserve.academy.Tips4Trips.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "planets")
public class Planet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30)
    @NotBlank
    @Column(nullable = false, length = 30)
    private String name;

    @OneToMany(mappedBy="planet", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Country> listOfCountries;

    public Planet(){}

    public Planet(@Size(max = 30) @NotBlank String name,
                  List<Country> listOfCountries) {
        this.name = name;
        this.listOfCountries = listOfCountries;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Country> getListOfCountries() {
        return listOfCountries;
    }

    public void setListOfCountries(List<Country> listOfCountries) {
        this.listOfCountries = listOfCountries;
    }
}