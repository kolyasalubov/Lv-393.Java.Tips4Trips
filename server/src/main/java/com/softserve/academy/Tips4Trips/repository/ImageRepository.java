package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.file.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findById(Long id);
}
