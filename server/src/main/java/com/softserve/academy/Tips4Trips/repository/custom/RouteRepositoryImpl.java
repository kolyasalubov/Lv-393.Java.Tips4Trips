package com.softserve.academy.Tips4Trips.repository.custom;

import com.softserve.academy.Tips4Trips.dto.search.RouteSearchParams;
import com.softserve.academy.Tips4Trips.entity.Route;
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
public class RouteRepositoryImpl implements RouteRepositoryCustom {

    private EntityManager em;

    @Autowired
    public RouteRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Route> findByParams(RouteSearchParams routeSearchParams) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Route> cq = cb.createQuery(Route.class);
        Root<Route> route = cq.from(Route.class);
        List<Predicate> predicates = new ArrayList<>();
        if (routeSearchParams.getName() != null) {
            predicates.add(cb.like(cb.upper(route.get("name")),
                    "%" + routeSearchParams.getName().toUpperCase() + "%"));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();
    }

}
