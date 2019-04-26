package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.FindGroup;
import com.softserve.academy.Tips4Trips.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface TripRepository extends JpaRepository<FindGroup, Long> {

    Page<FindGroup> findAllByOrderByIdDesc(Pageable pageable);

    List<FindGroup> findByNameContainingIgnoreCase(String name);

    List<FindGroup> findByCreator(Account creator);

    List<FindGroup> findByRoute(Route route);
}