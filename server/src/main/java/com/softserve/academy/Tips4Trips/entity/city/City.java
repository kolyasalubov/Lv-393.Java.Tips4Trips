package com.softserve.academy.Tips4Trips.entity.city;

import com.softserve.academy.Tips4Trips.entity.Country;
import com.softserve.academy.Tips4Trips.entity.Position;
import com.softserve.academy.Tips4Trips.entity.place.Place;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cities")
public class City implements Serializable {
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
    @JoinColumn(name = "country_id", referencedColumnName = "id",
            nullable = false)
    private Country country;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Place> listOfPlaces;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<CityFeedback> cityFeedbacks;

    public City() {
    }

    public City(@Size(max = 35) @NotBlank String name, @NotNull Position position, Country country, List<Place> listOfPlaces, List<CityFeedback> cityFeedbacks) {
        this.name = name;
        this.position = position;
        this.country = country;
        this.listOfPlaces = listOfPlaces;
        this.cityFeedbacks = cityFeedbacks;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Place> getListOfPlaces() {
        return listOfPlaces;
    }

    public void setListOfPlaces(List<Place> listOfPlaces) {
        this.listOfPlaces = listOfPlaces;
    }

    public List<CityFeedback> getCityFeedbacks() {
        return cityFeedbacks;
    }

    public void setCityFeedbacks(List<CityFeedback> cityFeedbacks) {
        this.cityFeedbacks = cityFeedbacks;
    }
}
