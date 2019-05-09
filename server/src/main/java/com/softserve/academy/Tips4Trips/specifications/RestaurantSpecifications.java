package com.softserve.academy.Tips4Trips.specifications;

import com.softserve.academy.Tips4Trips.entity.place.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class RestaurantSpecifications {
    public static Specification<Restaurant> city(Integer id) {
        return (place, cq, cb) ->
                (id == null) ? null
                        : cb.equal(place.get("city").get("id"), id);
    }

    public static Specification<Restaurant> inOpeningTime(Date inOpeningTime) {
        return (place, cq, cb) ->
                (inOpeningTime == null) ? null
                        : cb.lessThanOrEqualTo(place.get("openingTime"), inOpeningTime);
    }

    public static Specification<Restaurant> inClosingTime(Date inClosingTime) {
        return (place, cq, cb) ->
                (inClosingTime == null) ? null
                        : cb.greaterThanOrEqualTo(place.get("closingTime"), inClosingTime);
    }

    public static Specification<Restaurant> averageBill(Float averageBill) {
        return (place, cq, cb) ->
                (averageBill == null) ? null
                        : cb.lessThanOrEqualTo(place.get("averageBill"), averageBill);
    }

    public static Specification<Restaurant> hasVeganFood(Boolean hasVeganFood) {
        return (place, cq, cb) ->
                (hasVeganFood == null) ? null
                        :cb.equal(place.get("hasVeganFood"), hasVeganFood);
    }
}
