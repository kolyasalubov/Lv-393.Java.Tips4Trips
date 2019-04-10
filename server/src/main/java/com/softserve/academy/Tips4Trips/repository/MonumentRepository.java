package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.place.Monument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonumentRepository extends JpaRepository<Monument, Long> {
    List<Monument> findByName(String name);

    List<Monument> findByCityAndName(String city, String name);
}
