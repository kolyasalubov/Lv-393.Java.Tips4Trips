package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.controller.RouteController;
import com.softserve.academy.Tips4Trips.dto.Page;
import com.softserve.academy.Tips4Trips.dto.details.RouteDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.AccountInfoDTO;
import com.softserve.academy.Tips4Trips.dto.info.RouteInfoDTO;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.place.Place;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RouteConverter implements Converter<Route, RouteDetailsDTO> {

    private AccountService accountService;
    private AccountConverter accountConverter;
    private PlaceConverter placeConverter;
    private PlaceService placeService;

    @Autowired
    public RouteConverter(AccountService accountService,
                          AccountConverter accountConverter,
                          PlaceConverter placeConverter,
                          PlaceService placeService) {
        this.accountService = accountService;
        this.accountConverter = accountConverter;
        this.placeConverter = placeConverter;
        this.placeService = placeService;
    }

    @Override
    public RouteDetailsDTO convertToDTO(Route route) {
        RouteDetailsDTO routedetailsDTO = (RouteDetailsDTO)
                toInfoDTO(new RouteDetailsDTO(), route);
        routedetailsDTO.setVerified((boolean) route.isVerified());
        routedetailsDTO.setPlaces(placeConverter
                .convertToDTO(route.getListOfPlaces()));
        return routedetailsDTO;
    }

    @Override
    public Route convertToEntity(RouteDetailsDTO routeDetailsDTO) {
        Route route = new Route();
        route.setId(routeDetailsDTO.getId());
        route.setCreationDate(routeDetailsDTO.getCreationDate());
        route.setName(routeDetailsDTO.getName());
        route.setVerified(routeDetailsDTO.isVerified());
        Account author = accountService.findById(
                routeDetailsDTO.getAuthorInfo().getId());
        route.setAuthor(author);
        route.setListOfPlaces(routeDetailsDTO.getPlaces()
                .stream()
                .map(p -> placeService.findById(p.getId()))
                .collect(Collectors.toList()));
        return route;
    }

    public RouteInfoDTO convertToInfoDTO(Route route) {
        return toInfoDTO(new RouteInfoDTO(), route);
    }

    public List<RouteInfoDTO> convertToInfoDTO(final List<Route> routes) {
        List<RouteInfoDTO> dtos = new ArrayList<>();
        if (routes != null) {
            dtos = routes.stream().map(this::convertToInfoDTO).collect(Collectors.toList());
        }
        return dtos;
    }

    public Page<RouteInfoDTO> convertToInfoDTO(final Page<Route> routePage) {
        List<RouteInfoDTO> dtos = convertToInfoDTO(routePage.getContent());
        return new Page<>(dtos, routePage.getNumber(), routePage.getTotalPages());
    }

    private RouteInfoDTO toInfoDTO(RouteInfoDTO routeInfoDTO, Route route) {
        routeInfoDTO.setId(route.getId());
        routeInfoDTO.setName(route.getName());
        routeInfoDTO.setCreationDate(route.getCreationDate());
        List<Place> places = route.getListOfPlaces();
        routeInfoDTO.setBegin(placeConverter
                .convertToMiniDTO(places.get(0)));
        routeInfoDTO.setEnd(placeConverter
                .convertToMiniDTO(places.get(places.size() - 1)));
        AccountInfoDTO authorInfo = accountConverter
                .convertToInfoDTO(route.getAuthor());
        routeInfoDTO.setAuthorInfo(authorInfo);
        routeInfoDTO.setSelf(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder
                        .methodOn(RouteController.class)
                        .getById(route.getId()))
                .withSelfRel().getHref());
        return routeInfoDTO;
    }

}
