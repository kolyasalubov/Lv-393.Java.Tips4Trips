package com.softserve.academy.Tips4Trips.service.impl;

import com.softserve.academy.Tips4Trips.service.Service;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Service
public class ServiceIml<T,ID,R extends JpaRepository<T,ID>> implements Service<T,ID,R> {

}
