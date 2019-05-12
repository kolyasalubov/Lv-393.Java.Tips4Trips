package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.city.CityFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityFeedbackRepository extends JpaRepository<CityFeedback, Long> {

    List<CityFeedback> findByCityId(Long cityId);

}
