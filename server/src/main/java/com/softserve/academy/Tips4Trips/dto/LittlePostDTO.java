package com.softserve.academy.Tips4Trips.dto;

import com.softserve.academy.Tips4Trips.entity.blog.Post;

import java.util.Calendar;

public class LittlePostDTO {
    private Long id;
    private String name;
    private String date;
    private String month;
    private String year;
    private String description;
    private String photoPath;

    public LittlePostDTO(Post post) {
        id = post.getId();
        name = post.getName();
        Calendar cal = Calendar.getInstance();
        cal.setTime(post.getCreationDate());
        date = String.valueOf(cal.get(Calendar.DATE));
        month = String.valueOf(cal.get(Calendar.MONTH));
        year = String.valueOf(cal.get(Calendar.YEAR));
        description = post.getContent();
        photoPath = post.getPhotoPath();
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
