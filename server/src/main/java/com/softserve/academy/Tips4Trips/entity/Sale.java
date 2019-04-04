package com.softserve.academy.Tips4Trips.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "photo_path", length = 500)
    private String photoPath;

    @NotNull
    @Column(name = "creator")
    private Account creator;

    @NotNull
    @Column(name = "creation_date")
    @Temporal(value = TemporalType.DATE)
    private Date creationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_sale_post")
    private TypeOfSalePost typeOfSalePost;

    @NotNull
    @Column(name = "price")
    private float price;

    public Sale() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
        this.creator = creator;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public TypeOfSalePost getTypeOfSalePost() {
        return typeOfSalePost;
    }

    public void setTypeOfSalePost(TypeOfSalePost typeOfSalePost) {
        this.typeOfSalePost = typeOfSalePost;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
