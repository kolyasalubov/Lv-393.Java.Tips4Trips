package com.softserve.academy.Tips4Trips.security;

import com.softserve.academy.Tips4Trips.controller.AccountController;
import com.softserve.academy.Tips4Trips.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("A")
public final class AuthorizationComponent {

    private RouteService routeService;
    private AccountController accountController;

    @Autowired
    public AuthorizationComponent(RouteService routeService, AccountController accountController) {
        this.routeService = routeService;
        this.accountController = accountController;
    }

    /*public boolean mayEditRoute(final Long id) {
        Route route = routeService.findById(id);
        return route.getAuthor().getId().equals(accountController.getCurrentUser().getId())
                && !route.isVerified();
    }*/

}