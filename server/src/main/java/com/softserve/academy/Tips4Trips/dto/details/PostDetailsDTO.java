package com.softserve.academy.Tips4Trips.dto.details;

import com.softserve.academy.Tips4Trips.dto.file.ImageDTO;
import com.softserve.academy.Tips4Trips.dto.info.PostInfoDTO;
import com.softserve.academy.Tips4Trips.dto.info.RouteInfoDTO;

import java.util.List;

public class PostDetailsDTO extends PostInfoDTO {

    private String content;
    private RouteInfoDTO routeInfo;
    private String likes;
    private String comments;
    private AccountDetailsDTO author;
    private Long authorId;
    private String authorFirstName;
    private String authorLastName;

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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public AccountDetailsDTO getAuthor() {
        return author;
    }

    public void setAuthor(AccountDetailsDTO author) {
        this.author = author;
    }
}
