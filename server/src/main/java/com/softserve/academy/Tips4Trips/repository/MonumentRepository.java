package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.place.Monument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonumentRepository extends JpaRepository<Monument, Long>, JpaSpecificationExecutor<Monument> {
    List<Monument> findByName(String name);

    List<Monument> findByCity(City city);
}
