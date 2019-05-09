//package com.softserve.academy.Tips4Trips.mapper;
//
//import com.softserve.academy.Tips4Trips.dto.FeedbackPlaceDTO;
//import com.softserve.academy.Tips4Trips.entity.feedback.FeedbackPlace;
//import com.softserve.academy.Tips4Trips.service.AccountService;
//import com.softserve.academy.Tips4Trips.service.PlaceService;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
//@Component
//public class FeedbackPlaceMapper extends ModelMapper {
//
//    private AccountService accountService;
//    private PlaceService placeService;
//
//    @PostConstruct
//    private void post(){
//        this.createTypeMap(FeedbackPlaceDTO.class, FeedbackPlace.class)
//                .setPostConverter(converter -> {
//                    FeedbackPlace entity = converter.getDestination();
//                    FeedbackPlaceDTO dto = converter.getSource();
//                    entity.setCreator(accountService.findById(dto.getCreator().getId()));
//                    entity.setPlace(placeService.findById(dto.getPlace().getId()));
//                    entity.setComment(dto.getComment());
//                    entity.setMark(dto.getMark());
//                    return entity;
//                });
//    }
//    @Autowired
//    public FeedbackPlaceMapper(AccountService accountService, PlaceService placeService) {
//        this.accountService = accountService;
//        this.placeService = placeService;
//    }
//}
