package com.softserve.academy.Tips4Trips.dto.converter.reverse;

import com.softserve.academy.Tips4Trips.dto.FindGroupDTO;
import com.softserve.academy.Tips4Trips.dto.converter.Converter;
import com.softserve.academy.Tips4Trips.entity.Account;
import com.softserve.academy.Tips4Trips.entity.FindGroup;
import com.softserve.academy.Tips4Trips.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ReverseFindGroupConverter implements Converter<FindGroupDTO, FindGroup> {

    AccountService accountService;
    ReverseAccountConverter reverseAccountConverter;
    RouteService routeService;

    @Autowired
    public ReverseFindGroupConverter(AccountService accountService, ReverseAccountConverter reverseAccountConverter, RouteService routeService) {
        this.accountService = accountService;
        this.reverseAccountConverter = reverseAccountConverter;
        this.routeService = routeService;
    }

    @Override
    public FindGroup apply(FindGroupDTO findGroupDTO) {
        FindGroup findGroup = new FindGroup();
        findGroup.setId(findGroupDTO.getId());
        findGroup.setName(findGroupDTO.getName());
        findGroup.setDescription(findGroupDTO.getDescription());
        findGroup.setCreationDate(findGroupDTO.getCreationDate());
        findGroup.setStartDate(findGroupDTO.getStartDate());

        Optional<Account> account = accountService.findById(findGroupDTO.getAuthorId());
        account.ifPresent(findGroup::setCreator);

        Optional<Route> route = routeService.findById(findGroupDTO.getRouteId());
        route.ifPresent(findGroup::setRoute);

        findGroup.setSubscribers(findGroupDTO.getSubscribers().stream()
                .map(accountDTO -> reverseAccountConverter.apply(accountDTO))
                .collect(Collectors.toList())
        );
        return findGroup;


    }
}
