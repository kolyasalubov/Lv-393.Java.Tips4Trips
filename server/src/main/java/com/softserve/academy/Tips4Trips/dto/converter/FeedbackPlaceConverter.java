package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.FeedbackPlaceDTO;
import com.softserve.academy.Tips4Trips.dto.Page;
import com.softserve.academy.Tips4Trips.dto.details.PostDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.PostInfoDTO;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.entity.feedback.FeedbackPlace;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.PlaceService;
import com.softserve.academy.Tips4Trips.service.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class FeedbackPlaceConverter implements Converter<FeedbackPlace, FeedbackPlaceDTO> {

    private AccountService accountService;
    private PlaceService placeService;

    @Autowired
    public FeedbackPlaceConverter(AccountService accountService, PlaceService placeService) {
        this.accountService = accountService;
        this.placeService = placeService;
    }

    @Override
    public FeedbackPlaceDTO convertToDTO(FeedbackPlace feedbackPlace) {
        return null;
    }

    @Override
    public FeedbackPlace convertToEntity(FeedbackPlaceDTO dto) {
        FeedbackPlace entity = new FeedbackPlace();
        entity.setId(dto.getId());
        entity.setCreator(accountService.findById(dto.getCreator().getId()));
        entity.setPlace(placeService.findById(dto.getPlace().getId()));
        entity.setComment(dto.getComment());
        entity.setMark(dto.getMark());
        return entity;
    }


}
