package com.softserve.academy.Tips4Trips.repository.search;

import com.softserve.academy.Tips4Trips.dto.Page;
import com.softserve.academy.Tips4Trips.dto.search.RouteSearchParams;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.entity.place.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class RouteRepositoryImpl implements SearchRepository<Route, RouteSearchParams> {

    private EntityManager em;
    private CriteriaBuilder cb;

    @Autowired
    public RouteRepositoryImpl(EntityManager em) {
        this.em = em;
        cb = em.getCriteriaBuilder();
    }

    @Override
    public Page<Route> findByParams(RouteSearchParams searchParams, long page, int size) {
        CriteriaQuery<Route> cq = cb.createQuery(Route.class);
        Root<Route> route = cq.from(Route.class);
        List<Predicate> wherePredicates = getWherePredicates(searchParams, route);
        cq.where(wherePredicates.toArray(new Predicate[0]));
        long count = getCountByParams(searchParams);
        long total = (count % size == 0) ? count / size : count / size + 1;
        List<Route> result = em.createQuery(cq)
                .setFirstResult((int) page * size)
                .setMaxResults(size).getResultList();
        return new Page<>(result, page, total);
    }

    private long getCountByParams(RouteSearchParams searchParams) {
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Route> from = cq.from(Route.class);
        List<Predicate> wherePredicates = getWherePredicates(searchParams, from);
        cq.select(cb.count(from))
                .where(wherePredicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getSingleResult();
    }

    private List<Predicate> getWherePredicates(RouteSearchParams searchParams, Root<Route> route) {
        List<Predicate> predicates = new LinkedList<>();
        if (searchParams.getName() != null) {
            predicates.add(cb
                    .like(cb.upper(route.get("name")),
                    "%" + searchParams.getName().toUpperCase() + "%"));
        }
        if (searchParams.isVerifiedOnly()) {
            predicates.add(cb.equal(route.get("verified"), true));
        }
        if (searchParams.getStartDate() != null) {
            predicates.add(cb
                    .greaterThanOrEqualTo(route.get("creationDate"), searchParams.getStartDate()));
        }
        if (searchParams.getEndDate() != null) {
            predicates.add(cb
                    .lessThanOrEqualTo(route.get("creationDate"), searchParams.getEndDate()));
        }
        return predicates;
    }

}
