package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.FindGroup;
import com.softserve.academy.Tips4Trips.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FindGroupRepository extends JpaRepository<FindGroup, Long> {
    List<FindGroup> findByNameContainingIgnoreCase(String name);

    List<FindGroup> findByCreator(Account creator);

    List<FindGroup> findByRoute(Route route);
}
