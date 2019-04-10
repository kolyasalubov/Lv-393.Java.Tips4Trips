package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.FindGroupDTO;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.FindGroup;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.repository.RouteRepository;
import com.softserve.academy.Tips4Trips.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FindGroupConverter implements Converter<FindGroup, FindGroupDTO> {

    AccountConverter accountConverter;
    AccountService accountService;
    RouteRepository routeRepository;

    @Autowired
    public FindGroupConverter(AccountConverter accountConverter, AccountService accountService, RouteRepository routeRepository) {
        this.accountConverter = accountConverter;
        this.accountService = accountService;
        this.routeRepository = routeRepository;
    }

    @Override
    public FindGroupDTO convertToDTO(FindGroup findGroup) {
        FindGroupDTO findGroupDTO = new FindGroupDTO();
        findGroupDTO.setId(findGroup.getId());
        findGroupDTO.setName(findGroup.getName());
        findGroupDTO.setDescription(findGroup.getDescription());
        findGroupDTO.setCreationDate(findGroup.getCreationDate());
        findGroupDTO.setStartDate(findGroup.getStartDate());
        findGroupDTO.setAuthorId(findGroup.getCreator().getId());

        if (findGroup.getRoute() != null) {
            findGroupDTO.setRouteId(findGroup.getRoute().getId());
        }

        findGroupDTO.setSubscribers(
                findGroup.getSubscribers().stream()
                        .map(account -> accountConverter.convertToDTO(account))
                        .collect(Collectors.toList()));

        return findGroupDTO;
    }

    @Override
    public FindGroup convertToEntity(FindGroupDTO findGroupDTO) {
        FindGroup findGroup = new FindGroup();
        findGroup.setId(findGroupDTO.getId());
        findGroup.setName(findGroupDTO.getName());
        findGroup.setDescription(findGroupDTO.getDescription());
        findGroup.setCreationDate(findGroupDTO.getCreationDate());
        findGroup.setStartDate(findGroupDTO.getStartDate());

        Optional<Account> account = accountService.findById(findGroupDTO.getAuthorId());
        account.ifPresent(findGroup::setCreator);

        Optional<Route> route = routeRepository.findById(findGroupDTO.getRouteId());
        route.ifPresent(findGroup::setRoute);

        findGroup.setSubscribers(findGroupDTO.getSubscribers().stream()
                .map(accountDTO -> accountConverter.convertToEntity(accountDTO))
                .collect(Collectors.toList())
        );
        return findGroup;
    }


}
