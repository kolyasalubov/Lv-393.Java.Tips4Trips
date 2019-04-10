package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.RestaurantDTO;
import com.softserve.academy.Tips4Trips.dto.converter.RestaurantConverter;
import com.softserve.academy.Tips4Trips.entity.Position;
import com.softserve.academy.Tips4Trips.entity.enums.TypeOfBuilding;
import com.softserve.academy.Tips4Trips.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@RestController("/restaurants")
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    RestaurantConverter restaurantConverter;

    @PostMapping("/create")
    public void create(){
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setHasVeganFood(true);
        restaurantDTO.setAverageBill(15.2F);
        restaurantDTO.setAddress("asd");
        restaurantDTO.setCityId((long) 1);
        restaurantDTO.setClosingTime(new Date());
        restaurantDTO.setOpeningTime(new Date());
        restaurantDTO.setName("asd");
        restaurantDTO.setPhotoPath("asd");
        restaurantDTO.setPosition(new Position());
        restaurantDTO.setWebSite("asdasd");
        restaurantDTO.setDescription("asdas");
        restaurantDTO.setTelephone("fvgbhjk");
        restaurantDTO.setType(TypeOfBuilding.ENTERTAINMENT);
        restaurantDTO.setWorkingDays(new ArrayList<DayOfWeek>(Collections.singleton(DayOfWeek.MONDAY)));

        restaurantRepository.save(restaurantConverter.convertToEntity(restaurantDTO));
    }
}
