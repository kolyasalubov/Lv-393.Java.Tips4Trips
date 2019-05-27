package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.city.City;
import com.softserve.academy.Tips4Trips.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByName(String name);

    List<City> findByCountry(Country country);

    Page<City> findByCountryId(Long countryId, Pageable pageable);

    long countByCountryId(Long countryId);
}
