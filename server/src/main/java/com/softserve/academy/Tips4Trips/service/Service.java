package com.softserve.academy.Tips4Trips.service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Service<T, ID, R extends JpaRepository<T,ID>> {

}
