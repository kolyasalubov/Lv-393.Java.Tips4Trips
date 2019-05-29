package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.dto.search.TripSearchParams;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.Trip;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.repository.search.SearchRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

@Repository
public interface TripRepository
        extends JpaRepository<Trip, Long>, SearchRepository<Trip, TripSearchParams> {

    Page<Trip> findAllByOrderByIdDesc(Pageable pageable);

    List<Trip> findByNameContainingIgnoreCase(String name);

    List<Trip> findByCreator(Account creator);

    List<Trip> findByRoute(Route route);

    Page<Trip> findByCreatorIdOrderByIdDesc(Long id, Pageable pageable);
}
