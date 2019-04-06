package com.softserve.academy.Tips4Trips.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Service<T, ID, R extends JpaRepository<T, ID>> {
    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T entity);

    void delete(T entity);
}
