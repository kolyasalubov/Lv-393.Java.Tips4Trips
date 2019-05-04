package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.dto.search.RouteSearchParams;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.repository.search.SearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RouteRepository
        extends JpaRepository<Route, Long>, SearchRepository<Route, RouteSearchParams> {

    List<Route> findByAuthor(Account author);

    List<Route> findByVerified(boolean verified);

    Optional<Route> findByName(String name);
}
