package com.softserve.academy.Tips4Trips.dto.details;

import com.softserve.academy.Tips4Trips.dto.info.PostInfoDTO;
import com.softserve.academy.Tips4Trips.dto.info.RouteInfoDTO;

public class PostDetailsDTO extends PostInfoDTO {
    private String photoPath;
    private String content;
    private RouteInfoDTO routeInfo;
    private String likes;
    private String comments;
    private AccountDetailsDTO author;

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public RouteInfoDTO getRouteInfo() {
        return routeInfo;
    }

    public void setRouteInfo(RouteInfoDTO routeInfo) {
        this.routeInfo = routeInfo;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public AccountDetailsDTO getAuthor() {
        return author;
    }

    public void setAuthor(AccountDetailsDTO author) {
        this.author = author;
    }
}
