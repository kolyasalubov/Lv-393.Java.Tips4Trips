package com.softserve.academy.Tips4Trips.dto;

import com.softserve.academy.Tips4Trips.dto.details.AccountDetailsDTO;
import com.softserve.academy.Tips4Trips.entity.enums.FeedbackMark;

import java.util.Date;


public class FeedbackPlaceDTO {
    private Long id;
    private PlaceDTO place;
    private AccountDetailsDTO creator;
    private Date date;
    private FeedbackMark mark;
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlaceDTO getPlace() {
        return place;
    }

    public void setPlace(PlaceDTO place) {
        this.place = place;
    }

    public AccountDetailsDTO getCreator() {
        return creator;
    }

    public void setCreator(AccountDetailsDTO creator) {
        this.creator = creator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FeedbackMark getMark() {
        return mark;
    }

    public void setMark(FeedbackMark mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
