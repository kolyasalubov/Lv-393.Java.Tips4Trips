package com.softserve.academy.Tips4Trips.entity.file;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

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

    public Image() {
    }

    public Image(@NotBlank @Size(max = 50, min = 1) String name,
                 @NotBlank @Size(max = 4, min = 3) String format) {
        this.name = name;
        this.format = format;
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
}