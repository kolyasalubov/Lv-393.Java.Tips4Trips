package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.FindGroupDTO;
import com.softserve.academy.Tips4Trips.entity.FindGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FindGroupConverter implements Converter<FindGroup, FindGroupDTO> {

    AccountConverter accountConverter;

    @Autowired
    public FindGroupConverter(AccountConverter accountConverter) {
        this.accountConverter = accountConverter;
    }

    @Override
    public FindGroupDTO apply(FindGroup findGroup) {
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
                        .map(account -> accountConverter.apply(account))
                        .collect(Collectors.toList()));

        return findGroupDTO;
    }
}
