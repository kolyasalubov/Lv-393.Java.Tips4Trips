package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.Trip;
import com.softserve.academy.Tips4Trips.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    Page<Trip> findAllByOrderByIdDesc(Pageable pageable);

    List<Trip> findByNameContainingIgnoreCase(String name);

    List<Trip> findByCreator(Account creator);

    List<Trip> findByRoute(Route route);

}
