package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.city.City;
import com.softserve.academy.Tips4Trips.entity.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findByCity(City city);
    List<Place> findByNameIgnoreCase(String name);
    List<Place> findTop5ByNameContainingIgnoreCaseOrderByName(String name);
}
