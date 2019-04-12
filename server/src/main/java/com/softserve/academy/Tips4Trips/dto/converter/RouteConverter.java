package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.RouteDTO;
import com.softserve.academy.Tips4Trips.dto.info.RouteInfoDTO;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteConverter implements Converter<Route, RouteDTO> {

    AccountRepository accountRepository;
    RouteRepository routeRepository;

    @Autowired
    public RouteConverter(AccountRepository accountRepository,
                          RouteRepository routeRepository) {
        this.accountRepository = accountRepository;
        this.routeRepository = routeRepository;
    }

    @Override
    public RouteDTO convertToDTO(Route route) {
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setId(route.getId());
        routeDTO.setCreationDate(route.getCreationDate());
        routeDTO.setName(route.getName());
        routeDTO.setPhotoPath(route.getPhotoPath());
        routeDTO.setAuthorId(route.getAuthor().getId());
        return routeDTO;
    }

    @Override
    public Route convertToEntity(RouteDTO routeDTO) {
        long id = (routeDTO.getId() != null) ? routeDTO.getId() : 0;
        Route route = routeRepository.findById(id)
                .orElseGet(Route::new);
        route.setCreationDate(routeDTO.getCreationDate());
        route.setName(routeDTO.getName());
        route.setPhotoPath(routeDTO.getPhotoPath());
        accountRepository.findById(routeDTO.getAuthorId())
                .ifPresent(route::setAuthor);
        return route;
    }

    public RouteInfoDTO convertToInfoDTO(Route route) {
        RouteInfoDTO routeInfoDTO = new RouteInfoDTO();
        routeInfoDTO.setId(route.getId());
        return routeInfoDTO;
    }

}
