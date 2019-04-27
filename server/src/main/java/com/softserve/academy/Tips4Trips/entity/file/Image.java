package com.softserve.academy.Tips4Trips.entity.file;

import com.softserve.academy.Tips4Trips.entity.administration.Account;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "images")
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50, min = 1)
    @Column(nullable = false, length = 50)
    private String name;

    @NotBlank
    @Size(max = 4, min = 3)
    @Column(nullable = false, length = 4)
    private String format;

    @NotNull
    @PastOrPresent
    @Column(name = "upload_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date uploadDate;

    @NotNull
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account creator;

    public Image() {
    }

    public Image(@NotBlank @Size(max = 50, min = 1) String name,
                 @NotBlank @Size(max = 4, min = 3) String format,
                 @NotNull @PastOrPresent Date uploadDate,
                 @NotNull Account creator) {
        this.name = name;
        this.format = format;
        this.uploadDate = uploadDate;
        this.creator = creator;
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
        this.creator = creator;
    }
}