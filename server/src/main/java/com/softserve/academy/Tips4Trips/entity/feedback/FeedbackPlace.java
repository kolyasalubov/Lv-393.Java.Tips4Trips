package com.softserve.academy.Tips4Trips.entity.feedback;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.enums.FeedbackMark;
import com.softserve.academy.Tips4Trips.entity.place.Place;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "feedback_place")
public class FeedbackPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "place_id")
    private Place place;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id")
    private Account creator;

    @Column(name = "creation_date")
    @CreationTimestamp
    private Date date;


    @NotNull
    @Column(name = "mark",nullable = false)
    private FeedbackMark mark;

    @NotNull
    @Size(max = 255)
    @Column(name = "comment", nullable = false, length = 255)
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
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
