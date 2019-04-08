package com.softserve.academy.Tips4Trips.dto;

import java.util.Date;
import java.util.List;

public class FindGroupDTO {

    private Long id;

    private String name;

    private String description;

    private Date creationDate;

    private Date startDate;

    private Long authorId;

    private Long routeId;

    private List<AccountDTO> subscribers;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public List<AccountDTO> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<AccountDTO> subscribers) {
        this.subscribers = subscribers;
    }


}
