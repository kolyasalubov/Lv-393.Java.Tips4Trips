package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.place.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
