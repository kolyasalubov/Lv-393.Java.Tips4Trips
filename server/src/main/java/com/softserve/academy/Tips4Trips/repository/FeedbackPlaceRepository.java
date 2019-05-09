package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.feedback.FeedbackPlace;
import com.softserve.academy.Tips4Trips.entity.place.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackPlaceRepository extends JpaRepository<FeedbackPlace, Long> {
    Page<FeedbackPlace> findAllByPlaceIdOrderByDateDesc(Long place, Pageable pageable);
}
