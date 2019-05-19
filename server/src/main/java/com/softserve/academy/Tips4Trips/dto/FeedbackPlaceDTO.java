package com.softserve.academy.Tips4Trips.dto;

import com.softserve.academy.Tips4Trips.dto.details.AccountDetailsDTO;

import java.util.Date;


public class FeedbackPlaceDTO {
    private Long id;
    private MiniPlaceDTO place;
    private AccountDetailsDTO creator;
    private Date date;
    private Integer mark;
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MiniPlaceDTO getPlace() {
        return place;
    }

    public void setPlace(MiniPlaceDTO place) {
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

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
