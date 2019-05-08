package com.softserve.academy.Tips4Trips.repository.search;

import com.softserve.academy.Tips4Trips.dto.search.TripSearchParams;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class TripRepositoryImpl implements SearchRepository<Trip, TripSearchParams> {

    private EntityManager em;
    private CriteriaBuilder cb;

    @Autowired
    public TripRepositoryImpl(EntityManager em) {
        this.em = em;
        cb = em.getCriteriaBuilder();
    }

    @Override
    public List<Trip> findByParams(TripSearchParams searchParams) {
        CriteriaQuery<Trip> cq = cb.createQuery(Trip.class);
        Root<Trip> trip = cq.from(Trip.class);
        List<Predicate> wherePredicates = getWherePredicates(searchParams, trip);
        List<Predicate> havingPredicates = getHavingPredicates(searchParams, trip);
        cq.where(wherePredicates.toArray(new Predicate[0]))
                .groupBy(trip)
                .having(havingPredicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();
    }

    private List<Predicate> getWherePredicates(TripSearchParams searchParams, Root<Trip> trip) {
        List<Predicate> wherePredicates = new LinkedList<>();
        if (searchParams.getName() != null) {
            wherePredicates.add(cb
                    .like(cb.upper(trip.get("name")),
                            "%" + searchParams.getName().toUpperCase() + "%"));
        }
        if (searchParams.getRouteName() != null) {
            wherePredicates.add(cb
                    .like(trip.join("route").get("name"),
                            "%" + searchParams.getRouteName() + "%"));
        }
        if (searchParams.getStartDate() != null) {
            wherePredicates.add(cb
                    .greaterThanOrEqualTo(trip.get("creationDate"),
                            searchParams.getStartDate()));
        }
        if (searchParams.getEndDate() != null) {
            wherePredicates.add(cb
                    .lessThanOrEqualTo(trip.get("creationDate"),
                            searchParams.getEndDate()));
        }
        return wherePredicates;
    }

    private List<Predicate> getHavingPredicates(TripSearchParams searchParams, Root<Trip> trip) {
        List<Predicate> havingPredicates = new LinkedList<>();
        if (searchParams.getMinSubscribersCount() > 0) {
            havingPredicates.add(cb
                    .greaterThanOrEqualTo(cb
                    .count(trip.join("subscribers")), searchParams
                    .getMinSubscribersCount()));
        }
        return havingPredicates;
    }
}
