package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByName(String name);

}
