package com.softserve.academy.Tips4Trips.entity.place;

import com.softserve.academy.Tips4Trips.entity.city.City;
import com.softserve.academy.Tips4Trips.entity.Position;
import com.softserve.academy.Tips4Trips.entity.enums.PlaceCategory;
import com.softserve.academy.Tips4Trips.entity.file.Image;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "places")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Place implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "key_generator")

    private Long id;

    @Size(max = 35)
    @NotBlank
    @Column(nullable = false, length = 35)
    private String name;

    private String description;

    @Size(max = 60)
    @NotBlank
    @Column(nullable = false, length = 60)
    private String address;

    @Embedded
    @NotNull
    @Column(nullable = false)
    private Position position;

    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id",
            nullable = false)
    private City city;

    public Place() {
    }

    public Place(@Size(max = 35) @NotBlank String name, String description,
                 @Size(max = 60) @NotBlank String address,
                 @NotNull Position position, Image image,
                 @NotNull City city) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.position = position;
        this.image = image;
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public abstract PlaceCategory getCategory();
}
