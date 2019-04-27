package com.softserve.academy.Tips4Trips.repository;

import com.softserve.academy.Tips4Trips.entity.file.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long>{
}
