package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> findByNameContainingIgnoreCase(String name);

    List<Route> findByAuthor(Account author);

    Optional<Route> findByName(String name);
}
