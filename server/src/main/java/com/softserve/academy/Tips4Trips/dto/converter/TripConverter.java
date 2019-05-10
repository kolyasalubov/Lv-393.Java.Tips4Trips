package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.controller.TripController;
import com.softserve.academy.Tips4Trips.dto.Page;
import com.softserve.academy.Tips4Trips.dto.details.TripDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.TripInfoDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.Trip;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TripConverter implements Converter<Trip, TripDetailsDTO> {

    private AccountConverter accountConverter;
    private RouteConverter routeConverter;
    private AccountService accountService;
    private RouteService routeService;


    private final int MAX_DESCRIPTION_LENGTH = 100;

    @Autowired
    public TripConverter(AccountConverter accountConverter, AccountService accountService, RouteService routeService, RouteConverter routeConverter) {
        this.accountConverter = accountConverter;
        this.accountService = accountService;
        this.routeService = routeService;
        this.routeConverter = routeConverter;
    }

    @Override
    public Trip convertToEntity(TripDetailsDTO tripDetailsDTO) {
        Trip trip = new Trip();
        trip.setId(tripDetailsDTO.getId());
        trip.setName(tripDetailsDTO.getName());
        trip.setDescription(tripDetailsDTO.getDescription());
        trip.setCreationDate(tripDetailsDTO.getCreationDate());
        trip.setStartDate(tripDetailsDTO.getStartDate());
        Account creator = accountService.findById(tripDetailsDTO.getCreator().getId());
        trip.setCreator(creator);
        Route route = routeService.findById(tripDetailsDTO.getRoute().getId());
        trip.setRoute(route);
        trip.setSubscribers(tripDetailsDTO.getSubscribers().stream()
                .map(p -> accountService.findById(p.getId()))
                .collect(Collectors.toList())
        );

        return trip;
    }


    @Override
    public TripDetailsDTO convertToDTO(Trip trip) {
        TripDetailsDTO tripDetailsDTO = (TripDetailsDTO)
                toInfoDTO(new TripDetailsDTO(), trip);

        Account creator = trip.getCreator();
        tripDetailsDTO.setCreator(accountConverter.convertToDTO(creator));
        Route route = trip.getRoute();
        tripDetailsDTO.setRoute(routeConverter.convertToDTO(route));

        List<Account> accountInfoDTOS = trip.getSubscribers();
        tripDetailsDTO.setSubscribers(accountConverter.convertToInfoDTO(accountInfoDTOS));

        return tripDetailsDTO;
    }


    public List<TripInfoDTO> convertToInfoDTO(final List<Trip> tripList) {
        List<TripInfoDTO> dtos = new ArrayList<>();
        if (tripList != null) {
            dtos = tripList.stream().map(this::convertToInfoDTO).collect(Collectors.toList());
        }
        return dtos;
    }


    public TripInfoDTO convertToInfoDTO(Trip trip) {
        return toInfoDTO(new TripInfoDTO(), trip);
    }

    public Page<TripInfoDTO> convertToInfoDTO(final Page<Trip> tripPage) {
        List<TripInfoDTO> dtos = convertToInfoDTO(tripPage.getList());
        return new Page<>(dtos, tripPage.getPage(), tripPage.getTotal());
    }


    private TripInfoDTO toInfoDTO(TripInfoDTO tripInfoDTO, Trip trip) {
        tripInfoDTO.setId(trip.getId());
        tripInfoDTO.setName(trip.getName());

        String description = trip.getDescription();
        String shortDescription = description.length() > MAX_DESCRIPTION_LENGTH
                ? description.substring(0, MAX_DESCRIPTION_LENGTH) : description;
        tripInfoDTO.setDescription(shortDescription + "...");

        tripInfoDTO.setDescription(description + "...");
        tripInfoDTO.setCreationDate(trip.getCreationDate());
        tripInfoDTO.setStartDate(trip.getStartDate());
        tripInfoDTO.setSelf(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder
                        .methodOn(TripController.class)
                        .getById(trip.getId()))
                .withSelfRel().getHref());

        return tripInfoDTO;
    }

}
