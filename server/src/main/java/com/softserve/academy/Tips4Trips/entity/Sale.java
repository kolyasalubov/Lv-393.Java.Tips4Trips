package com.softserve.academy.Tips4Trips.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "photo_path", length = 500, nullable = false)
    private String photoPath;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id", nullable = false)
    private Account creator;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date creationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_sale_post", nullable = false)
    private TypeOfSalePost typeOfSalePost;

    @NotNull
    @Column(name = "price", nullable = false)
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
