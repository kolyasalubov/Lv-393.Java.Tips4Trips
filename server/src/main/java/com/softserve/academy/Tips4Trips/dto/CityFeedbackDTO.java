package com.softserve.academy.Tips4Trips.dto;

import java.sql.Date;

public class CityFeedbackDTO {

    private Long id;
    private Long cityId;
    private String content;
    private Date creationDate;
    private String status;
    private String author;
    private Double averageRating;
    private Integer weatherRating;
    private Integer safetyRating;
    private Integer transportRating;
    private Integer costOfLivingRating;
    private Integer entertainmentRating;


    public CityFeedbackDTO() {
    }

    public CityFeedbackDTO(Long id,
                           Long cityId,
                           String content,
                           Date creationDate,
                           String status,
                           String author,
                           Double averageRating,
                           Integer weatherRating,
                           Integer safetyRating,
                           Integer transportRating,
                           Integer costOfLivingRating,
                           Integer entertainmentRating) {
        this.id = id;
        this.cityId = cityId;
        this.content = content;
        this.creationDate = creationDate;
        this.status = status;
        this.author = author;
        this.averageRating = averageRating;
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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
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

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
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
