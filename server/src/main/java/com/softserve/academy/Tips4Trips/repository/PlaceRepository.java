package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

}
