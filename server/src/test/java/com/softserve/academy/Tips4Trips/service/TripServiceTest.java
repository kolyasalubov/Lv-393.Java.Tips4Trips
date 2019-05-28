package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.Trip;
import com.softserve.academy.Tips4Trips.entity.enums.Role;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;
import com.softserve.academy.Tips4Trips.exception.DuplicateValueException;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.TripRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TripServiceTest {

    @Autowired
    private TripService tripService;

    @MockBean
    private TripRepository tripRepository;

    @MockBean
    private AccountRepository accountRepository;

    private Long existingTripId = 1L;
    private Long notExistingTripId = -1L;

    private Long existingAccountId = 1L;
    private Long notSubscribedAccountId = 2L;


    private Long tripId = 1L;
    private List<Account> subscribers =null;
    private Account subscribedAccount = null;
    private Account notSubscribedAccount = null;

    private Trip trip = null;
    @Before
    public void setUp(){
        trip = new Trip();
        trip.setId(tripId);

        notSubscribedAccount = new Account();
        notSubscribedAccount.setId(2L);
        notSubscribedAccount.setFirstName("firstname");
        notSubscribedAccount.setLastName("lastname");
        notSubscribedAccount.setEmail("email@mail.com");
        notSubscribedAccount.setPhoneNumber("0671111111");
        notSubscribedAccount.setRole(Role.MODERATOR);

        subscribedAccount = new Account();
        subscribedAccount.setId(1L);
        subscribedAccount.setFirstName("Bond");
        subscribedAccount.setLastName("James Bond");
        subscribedAccount.setEmail("anonymous@mail.com");
        subscribedAccount.setPhoneNumber("0671212121");
        subscribedAccount.setRole(Role.ADMIN);
        subscribers = new ArrayList<>();
        subscribers.add(subscribedAccount);

        trip.setSubscribers(subscribers);

        Mockito.when(tripRepository.findById(existingTripId))
                .thenReturn(Optional.of(trip));

        Mockito.when(accountRepository.findById(existingAccountId))
                .thenReturn(Optional.of(subscribedAccount));

        Mockito.when(accountRepository.findById(notSubscribedAccountId))
                .thenReturn(Optional.of(notSubscribedAccount));

    }


    @After
    public void tearDown(){
        trip = null;
        subscribedAccount = null;
        notSubscribedAccount = null;
        subscribers = null;

    }


    @Test
    public void whenValidId_thenTripShouldBeFound(){
        Trip found = tripService.findById(existingTripId);
        verifyFindByIdIsCalledOnce();
        assertThat(found.getId()).isEqualTo(tripId);
    }



    @Test(expected = DataNotFoundException.class)
    public void whenNonValidId_thenChatShouldNotBeFound(){
        Trip found = tripService.findById(notExistingTripId);
        verifyFindByIdIsCalledOnce();
        assertThat(found).isNull();
    }

    @Test
    public void whenSubscribe_thenAccountShouldBeReturned(){
        Account subscribedAccount = tripService.subscribe(existingTripId,notSubscribedAccountId);
        assertThat(subscribedAccount.getId()).isEqualTo(notSubscribedAccountId);
    }

    @Test (expected = DuplicateValueException.class)
    public void whenSubscribeExisting_thenAccountShouldNotBeReturned(){
        Account subscribedAccount =  tripService.subscribe(existingTripId,existingAccountId);
        assertThat(subscribedAccount).isNull();
    }


    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(tripRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(tripRepository);
    }
}
