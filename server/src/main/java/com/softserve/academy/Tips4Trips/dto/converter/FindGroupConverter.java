package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.controller.TripController;
import com.softserve.academy.Tips4Trips.dto.details.TripDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.TripInfoDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.FindGroup;
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
public class FindGroupConverter implements Converter<FindGroup, TripDetailsDTO> {

    private AccountConverter accountConverter;
    private RouteConverter routeConverter;
    private AccountService accountService;
    private RouteService routeService;


    private final int MAX_DESCRIPTION_LENGTH = 100;

    @Autowired
    public FindGroupConverter(AccountConverter accountConverter, AccountService accountService, RouteService routeService, RouteConverter routeConverter) {
        this.accountConverter = accountConverter;
        this.accountService = accountService;
        this.routeService = routeService;
        this.routeConverter = routeConverter;
    }

    @Override
    public FindGroup convertToEntity(TripDetailsDTO findGroupDetailsDTO) {
        FindGroup findGroup = new FindGroup();
        findGroup.setId(findGroupDetailsDTO.getId());
        findGroup.setName(findGroupDetailsDTO.getName());
        findGroup.setDescription(findGroupDetailsDTO.getDescription());
        findGroup.setCreationDate(findGroupDetailsDTO.getCreationDate());
        findGroup.setStartDate(findGroupDetailsDTO.getStartDate());
        Account creator = accountService.findById(findGroupDetailsDTO.getCreator().getId());
        findGroup.setCreator(creator);
        Route route = routeService.findById(findGroupDetailsDTO.getRoute().getId());
        findGroup.setRoute(route);
        findGroup.setSubscribers(findGroupDetailsDTO.getSubscribers().stream()
                .map(p -> accountService.findById(p.getId()))
                .collect(Collectors.toList())
        );

        return findGroup;
    }


    @Override
    public TripDetailsDTO convertToDTO(FindGroup findGroup) {
        TripDetailsDTO findGroupDetailsDTO = (TripDetailsDTO)
                toInfoDTO(new TripDetailsDTO(), findGroup);

        Account creator = findGroup.getCreator();
        findGroupDetailsDTO.setCreator(accountConverter.convertToDTO(creator));
        Route route = findGroup.getRoute();
        findGroupDetailsDTO.setRoute(routeConverter.convertToDTO(route));

        List<Account> accountInfoDTOS = findGroup.getSubscribers();
        findGroupDetailsDTO.setSubscribers(accountConverter.convertToInfoDTO(accountInfoDTOS));

        return findGroupDetailsDTO;
    }


    public List<TripInfoDTO> convertToInfoDTO(final List<FindGroup> findGroupList) {
        List<TripInfoDTO> dtos = new ArrayList<>();
        if (findGroupList != null) {
            dtos = findGroupList.stream().map(this::convertToInfoDTO).collect(Collectors.toList());
        }
        return dtos;
    }


    public TripInfoDTO convertToInfoDTO(FindGroup findGroup) {
        return toInfoDTO(new TripInfoDTO(), findGroup);
    }


    private TripInfoDTO toInfoDTO(TripInfoDTO tripInfoDTO, FindGroup findGroup) {
        tripInfoDTO.setId(findGroup.getId());
        tripInfoDTO.setName(findGroup.getName());

        String description = findGroup.getDescription();
        String shortDescription = description.length() > MAX_DESCRIPTION_LENGTH
                ? description.substring(0, MAX_DESCRIPTION_LENGTH) : description;
        tripInfoDTO.setDescription(shortDescription + "...");

        tripInfoDTO.setDescription(description + "...");
        tripInfoDTO.setCreationDate(findGroup.getCreationDate());
        tripInfoDTO.setStartDate(findGroup.getStartDate());
        tripInfoDTO.setSelf(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder
                        .methodOn(TripController.class)
                        .getById(findGroup.getId()))
                .withSelfRel().getHref());

        return tripInfoDTO;
    }

}
