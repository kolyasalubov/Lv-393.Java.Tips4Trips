package com.softserve.academy.Tips4Trips.entity.entertainment.mountains;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.Route;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trips")
public class Trip {
    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", startDate=" + startDate +
                ", creator=" + creator +
                ", route=" + route +
                ", subscribers=" + subscribers +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name",nullable = false)
    private String name;

    @NotNull
    @Column(name = "description",nullable = false)
    private String description;

    @NotNull
    @Column(name = "creation_date",nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date creationDate;

    @NotNull
    @Column(name = "start_date",nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date startDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "creator_id",referencedColumnName = "id",nullable = false)
    private Account creator;

    @NotNull
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "route_id",referencedColumnName = "id",nullable = false)
    private Route route;

    @ManyToMany
    @JoinTable( name="subscriber_group",
            joinColumns=
            @JoinColumn(name="subscriber_id", referencedColumnName="id")
            ,
            inverseJoinColumns=

            @JoinColumn(name="group_id", referencedColumnName="id")
             )
    private List<Account> subscribers;

    public Trip() {
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

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
        this.creator = creator;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<Account> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Account> subscribers) {
        this.subscribers = subscribers;
    }

    public void addSubscriber(Account account){
        subscribers.add(account);
    }

    public void removeSubscriber(Account account){
        subscribers.remove(account);
    }
}
