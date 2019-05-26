package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.entity.enums.Role;
import com.softserve.academy.Tips4Trips.entity.place.Hotel;
import com.softserve.academy.Tips4Trips.entity.place.Monument;
import com.softserve.academy.Tips4Trips.entity.place.Place;
import com.softserve.academy.Tips4Trips.entity.place.Restaurant;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;
import com.softserve.academy.Tips4Trips.exception.InvalidDataException;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.PostRepository;
import com.softserve.academy.Tips4Trips.repository.RouteRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.Assert.*;

public class RouteServiceTest {
    private RouteService routeService;
    private RouteRepository routeRepository = mock(RouteRepository.class);
    private AccountRepository accountRepository = mock(AccountRepository.class);
    private PostRepository postRepository = mock(PostRepository.class);

    private Account userAccount;
    private Account adminAccount;
    private Long nonExistingAccountId;

    private Route nonVerifiedRoute;
    private Route verifiedRoute;
    private Long nonExistingRouteId;

    private Post postAboutRoute;

    @Before
    public void setUp() {
        routeService = new RouteService(routeRepository,
                accountRepository, postRepository);

        userAccount = new Account();
        userAccount.setId(1L);
        userAccount.setRole(Role.USER);

        adminAccount = new Account();
        adminAccount.setId(2L);
        adminAccount.setRole(Role.ADMIN);

        nonExistingAccountId = 300L;

        nonVerifiedRoute = new Route();
        nonVerifiedRoute.setVerified(false);
        nonVerifiedRoute.setId(1L);
        nonVerifiedRoute.setAuthor(userAccount);
        nonVerifiedRoute.setName("route1");
        nonVerifiedRoute.setListOfPlaces(Arrays.asList(new Hotel(), new Restaurant()));
        nonVerifiedRoute.setCreationDate(new Date());

        verifiedRoute = new Route();
        verifiedRoute.setId(2L);
        verifiedRoute.setVerified(true);
        verifiedRoute.setAuthor(adminAccount);
        verifiedRoute.setName("route2");
        verifiedRoute.setListOfPlaces(Arrays.asList(new Restaurant(), new Hotel()));
        verifiedRoute.setCreationDate(new Date());

        nonExistingRouteId = 300L;

        postAboutRoute = new Post();
        postAboutRoute.setRoute(nonVerifiedRoute);

        Mockito.doReturn(Optional.of(verifiedRoute))
                .when(routeRepository).findById(verifiedRoute.getId());

        Mockito.doReturn(Optional.of(nonVerifiedRoute))
                .when(routeRepository).findById(nonVerifiedRoute.getId());

        Mockito.doReturn(Optional.empty())
                .when(routeRepository).findById(nonExistingRouteId);

        Mockito.doReturn(Collections.singletonList(verifiedRoute))
                .when(routeRepository).findByAuthor(adminAccount);

        Mockito.doReturn(Collections.singletonList(nonVerifiedRoute))
                .when(routeRepository).findByAuthor(userAccount);

        Mockito.doReturn(Optional.of(userAccount))
                .when(accountRepository).findById(userAccount.getId());

        Mockito.doReturn(Optional.of(adminAccount))
                .when(accountRepository).findById(adminAccount.getId());

        Mockito.doReturn(Optional.empty())
                .when(accountRepository).findById(nonExistingAccountId);

        Mockito.doReturn(Arrays.asList(new Post(), new Post(), postAboutRoute))
                .when(postRepository).findByRoute(nonVerifiedRoute);
    }

    @Test
    public void whenValidId_ThenRouteShouldBeFound() {
        Route nonVerified = routeService.findById(nonVerifiedRoute.getId());
        Route verified = routeService.findById(verifiedRoute.getId());

        assertEquals(verifiedRoute, verified);
        assertEquals(nonVerified, nonVerifiedRoute);

        Mockito.verify(routeRepository, Mockito.times(2))
                .findById(anyLong());
    }

    @Test(expected = DataNotFoundException.class)
    public void whenInvalidRouteId_ThenExceptionIsThrown() {
        routeService.findById(nonExistingRouteId);
        Mockito.verify(routeRepository, Mockito.times(1))
                .findById(anyLong());
    }

    @Test
    public void whenValidAuthorId_ThenRoutesShouldBeFound() {
        List<Route> routesByUser = routeService
                .findByAuthorId(userAccount.getId());
        List<Route> routesByAdmin = routeService
                .findByAuthorId(adminAccount.getId());
        routesByUser.forEach(route -> assertEquals(userAccount, route.getAuthor()));
        routesByAdmin.forEach(route -> assertEquals(adminAccount, route.getAuthor()));

        Mockito.verify(routeRepository, Mockito.times(2))
                .findByAuthor(any());
        Mockito.verify(accountRepository, Mockito.times(2))
                .findById(anyLong());
    }

    @Test(expected = DataNotFoundException.class)
    public void whenInvalidAuthorId_ThenExceptionIsThrown() {
        routeService.findByAuthorId(nonExistingAccountId);
        Mockito.verify(accountRepository, Mockito.times(1))
                .findById(nonExistingAccountId);
    }

    @Test
    public void whenRouteIsValid_ThenItisCreatedWithProperVerifiedField() {
        nonVerifiedRoute.setVerified(true);
        nonVerifiedRoute.setId(nonExistingRouteId);
        routeService.createRoute(nonVerifiedRoute);
        Mockito.verify(routeRepository, Mockito.times(1))
                .save(argThat(route -> route.getId() == -1L && !route.isVerified()
                        && route.getListOfPlaces().size() >= 2));
        Mockito.reset(routeRepository);
        verifiedRoute.setId(nonExistingRouteId);
        verifiedRoute.setVerified(false);
        routeService.createRoute(verifiedRoute);
        Mockito.verify(routeRepository, Mockito.times(1))
                .save(argThat(route -> route.getId() == -1L && route.isVerified()
                        && route.getListOfPlaces().size() >= 2));
    }

    @Test(expected = InvalidDataException.class)
    public void whenRouteHasLessThan2Places_ThenItIsNotCreated() {
        Route route = new Route();
        route.setListOfPlaces(Collections.singletonList(new Hotel()));
        routeService.createRoute(route);
    }

    @Test
    public void whenRouteIsFound_ThenItIsUpdated() {
        Route updatedRoute = new Route();
        updatedRoute.setId(nonVerifiedRoute.getId());
        updatedRoute.setName("new name");
        updatedRoute.setListOfPlaces(Arrays
                .asList(new Hotel(), new Restaurant(), new Monument()));
        routeService.update(updatedRoute);
        Mockito.verify(routeRepository, Mockito.times(1))
                .save(argThat(route -> route.getId().equals(updatedRoute.getId())
                        && route.getName().equals(updatedRoute.getName())
                        && route.getListOfPlaces().equals(updatedRoute.getListOfPlaces())));
    }

    @Test(expected = DataNotFoundException.class)
    public void whenRouteIsNotFound_ThenItIsNotUpdated() {
        Route updatedRoute = new Route();
        updatedRoute.setId(nonExistingRouteId);
        updatedRoute.setListOfPlaces(nonVerifiedRoute.getListOfPlaces());
        routeService.update(updatedRoute);
    }

    @Test(expected = InvalidDataException.class)
    public void whenUpdatedRouteHasLessThan2Places_ThenItIsNotUpdated() {
        Route updatedRoute = new Route();
        updatedRoute.setId(nonVerifiedRoute.getId());
        updatedRoute.setListOfPlaces(Collections.singletonList(new Hotel()));
        updatedRoute.setName("new name");
        routeService.update(updatedRoute);
    }

    @Test
    public void whenNotUpdatableDataIsUpdated_ThenItIsNotUpdated() {
        Route updatedRoute = new Route();
        updatedRoute.setId(nonVerifiedRoute.getId());
        updatedRoute.setCreationDate(Date.from(LocalDate.of(2019, 1, 1)
                .atStartOfDay(ZoneId.systemDefault()).toInstant()));
        updatedRoute.setVerified(true);
        updatedRoute.setAuthor(adminAccount);
        updatedRoute.setListOfPlaces(Arrays.asList(new Monument(), new Hotel()));
        routeService.update(updatedRoute);
        Mockito.verify(routeRepository, Mockito.times(1))
                .save(argThat(route -> !route.getAuthor().equals(updatedRoute.getAuthor())
                        && route.isVerified() != updatedRoute.isVerified()
                        && !route.getCreationDate().equals(updatedRoute.getCreationDate())));
    }

    @Test
    public void notVerifiedRoutesCanBeVerified() {
        routeService.verify(nonVerifiedRoute.getId());
        Mockito.verify(routeRepository, Mockito.times(1))
                .save(argThat(Route::isVerified));
    }

    @Test(expected = InvalidDataException.class)
    public void verifiedRoutesCantBeVerified() {
        routeService.verify(verifiedRoute.getId());
    }

    @Test
    public void whenRoutesAreDeleted_ThenRelatedPostsFieldsAreSetToNull() {
        routeService.deleteById(nonVerifiedRoute.getId());
        assertNull(postAboutRoute.getRoute());
        Mockito.verify(routeRepository, Mockito.times(1))
                .delete(nonVerifiedRoute);
    }


}
