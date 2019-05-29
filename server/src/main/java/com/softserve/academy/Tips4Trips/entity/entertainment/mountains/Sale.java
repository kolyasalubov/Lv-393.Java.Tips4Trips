package com.softserve.academy.Tips4Trips.entity.entertainment.mountains;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.file.Image;

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

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id",
            nullable = false)
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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
