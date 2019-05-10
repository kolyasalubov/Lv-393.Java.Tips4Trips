package com.softserve.academy.Tips4Trips.entity.city;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "cityFeedback")
public class CityFeedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    private City city;
    private String content;
    private LocalDateTime creationDate;
    private String status;
    private String author;
    private Integer weatherRating;
    private Integer safetyRating;
    private Integer transportRating;
    private Integer costOfLivingRating;
    private Integer entertainmentRating;

    public CityFeedback() {
    }

    public CityFeedback(City city, String content, LocalDateTime creationDate, String status, String author, Integer weatherRating, Integer safetyRating, Integer transportRating, Integer costOfLivingRating, Integer entertainmentRating) {
        this.city = city;
        this.content = content;
        this.creationDate = creationDate;
        this.status = status;
        this.author = author;
        this.weatherRating = weatherRating;
        this.safetyRating = safetyRating;
        this.transportRating = transportRating;
        this.costOfLivingRating = costOfLivingRating;
        this.entertainmentRating = entertainmentRating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getWeatherRating() {
        return weatherRating;
    }

    public void setWeatherRating(Integer weatherRating) {
        this.weatherRating = weatherRating;
    }

    public Integer getSafetyRating() {
        return safetyRating;
    }

    public void setSafetyRating(Integer safetyRating) {
        this.safetyRating = safetyRating;
    }

    public Integer getTransportRating() {
        return transportRating;
    }

    public void setTransportRating(Integer transportRating) {
        this.transportRating = transportRating;
    }

    public Integer getCostOfLivingRating() {
        return costOfLivingRating;
    }

    public void setCostOfLivingRating(Integer costOfLivingRating) {
        this.costOfLivingRating = costOfLivingRating;
    }

    public Integer getEntertainmentRating() {
        return entertainmentRating;
    }

    public void setEntertainmentRating(Integer entertainmentRating) {
        this.entertainmentRating = entertainmentRating;
    }
}

