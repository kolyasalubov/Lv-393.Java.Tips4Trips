package com.softserve.academy.Tips4Trips.specifications;

import com.softserve.academy.Tips4Trips.entity.place.Hotel;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class HotelSpecifications {
    public static Specification<Hotel> city(Integer id) {
        return (place, cq, cb) ->
                (id == null) ? null
                        : cb.equal(place.get("city").get("id"), id);
    }

    public static Specification<Hotel> inOpeningTime(Date inOpeningTime) {
        return (place, cq, cb) ->
                (inOpeningTime == null) ? null
                        : cb.lessThanOrEqualTo(place.get("openingTime"), inOpeningTime);
    }

    public static Specification<Hotel> inClosingTime(Date inClosingTime) {
        return (place, cq, cb) ->
                (inClosingTime == null) ? null
                        : cb.greaterThanOrEqualTo(place.get("closingTime"), inClosingTime);
    }

    public static Specification<Hotel> priceIn(Float priceIn) {
        return (place, cq, cb) ->
                (priceIn == null) ? null
                        : cb.lessThanOrEqualTo(place.get("minimumPrice"), priceIn);
    }

}
