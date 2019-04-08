package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
