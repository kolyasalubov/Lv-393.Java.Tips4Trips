package com.softserve.academy.Tips4Trips.entity.location;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "country")
public class Country implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 35)
    @NotBlank
    @Column(nullable = false, length = 35)
    private String name;

    @Embedded
    @NotNull
    @Column(nullable = false)
    private Position position;

    @ManyToOne
    @JoinColumn(name="planet_id", referencedColumnName = "id",
            nullable=false)
    private Planet planet;

    @OneToMany(mappedBy="country", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<City> listOfCities;

    public Country() {}

    public Country(@Size(max = 35) @NotBlank String name,
                   @NotNull Position position, Planet planet,
                   List<City> listOfCities) {
        this.name = name;
        this.position = position;
        this.planet = planet;
        this.listOfCities = listOfCities;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public List<City> getListOfCities() {
        return listOfCities;
    }

    public void setListOfCities(List<City> listOfCities) {
        this.listOfCities = listOfCities;
    }
}