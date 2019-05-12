package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.city.City;
import com.softserve.academy.Tips4Trips.entity.place.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant> {
    List<Restaurant> findByName(String name);

    List<Restaurant> findByCity(City city);
}
