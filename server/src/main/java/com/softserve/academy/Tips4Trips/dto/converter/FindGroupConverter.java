package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.controller.FindGroupController;
import com.softserve.academy.Tips4Trips.dto.FindGroupDTO;
import com.softserve.academy.Tips4Trips.dto.details.FindGroupDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.AccountInfoDTO;
import com.softserve.academy.Tips4Trips.dto.info.FindGroupInfoDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.FindGroup;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.repository.RouteRepository;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import javax.security.sasl.AuthorizeCallback;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FindGroupConverter implements Converter<FindGroup, FindGroupDetailsDTO> {

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
    public FindGroup convertToEntity(FindGroupDetailsDTO findGroupDetailsDTO) {
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
    public FindGroupDetailsDTO convertToDTO(FindGroup findGroup) {
        FindGroupDetailsDTO findGroupDetailsDTO = (FindGroupDetailsDTO)
                toInfoDTO(new FindGroupDetailsDTO(), findGroup);

        Account creator = findGroup.getCreator();
        findGroupDetailsDTO.setCreator(accountConverter.convertToDTO(creator));
        Route route = findGroup.getRoute();
        findGroupDetailsDTO.setRoute(routeConverter.convertToDTO(route));
        List<Account> accountInfoDTOS = findGroup.getSubscribers();
        findGroupDetailsDTO.setSubscribers(accountConverter.convertToInfoDTO(accountInfoDTOS));

        return findGroupDetailsDTO;
    }


    public List<FindGroupInfoDTO> convertToInfoDTO(final List<FindGroup> findGroupList) {
        List<FindGroupInfoDTO> dtos = new ArrayList<>();
        if (findGroupList != null) {
            dtos = findGroupList.stream().map(this::convertToInfoDTO).collect(Collectors.toList());
        }
        return dtos;
    }


    public FindGroupInfoDTO convertToInfoDTO(FindGroup findGroup) {
        return toInfoDTO(new FindGroupInfoDTO(), findGroup);
    }


    private FindGroupInfoDTO toInfoDTO(FindGroupInfoDTO findGroupInfoDTO, FindGroup findGroup) {
        findGroupInfoDTO.setId(findGroup.getId());
        findGroupInfoDTO.setName(findGroup.getName());

        String description = findGroup.getDescription();
        String shortDescription = description.length() > MAX_DESCRIPTION_LENGTH
                ? description.substring(0, MAX_DESCRIPTION_LENGTH) : description;
        findGroupInfoDTO.setDescription(shortDescription + "...");

        findGroupInfoDTO.setDescription(description + "...");
        findGroupInfoDTO.setCreationDate(findGroup.getCreationDate());
        findGroupInfoDTO.setStartDate(findGroup.getStartDate());
        findGroupInfoDTO.setSelf(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder
                        .methodOn(FindGroupController.class)
                        .getById(findGroup.getId()))
                .withSelfRel().getHref());

        return findGroupInfoDTO;
    }

}
