package com.softserve.academy.Tips4Trips.repository.search;

import com.softserve.academy.Tips4Trips.dto.search.TripSearchParams;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TripRepositoryImpl implements SearchRepository<Trip, TripSearchParams> {

    private EntityManager em;

    @Autowired
    public TripRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Trip> findByParams(TripSearchParams searchParams) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Trip> cq = cb.createQuery(Trip.class);
        Root<Trip> route = cq.from(Trip.class);
        List<Predicate> predicates = new ArrayList<>();
        if (searchParams.getName() != null) {
            predicates.add(cb.like(cb.upper(route.get("name")),
                    "%" + searchParams.getName().toUpperCase() + "%"));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();
    }
}
