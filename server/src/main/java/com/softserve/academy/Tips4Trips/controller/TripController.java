package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.AccountConverter;
import com.softserve.academy.Tips4Trips.dto.converter.TripConverter;
import com.softserve.academy.Tips4Trips.dto.details.TripDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.AccountInfoDTO;
import com.softserve.academy.Tips4Trips.dto.info.TripInfoDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.Trip;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.TripService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/trips")
public class TripController {

    private static final Logger logger = Logger.getLogger(TripController.class);

    private TripService tripService;
    private TripConverter tripConverter;
    private AccountService accountService;
    private AccountConverter accountConverter;

    @Autowired
    public TripController(TripService tripService, TripConverter tripConverter, AccountService accountService, AccountConverter accountConverter) {
        this.tripService = tripService;
        this.tripConverter = tripConverter;
        this.accountService = accountService;
        this.accountConverter = accountConverter;
    }

    @GetMapping
    public ResponseEntity<List<TripInfoDTO>> getAll() {
        logger.info("find group get all method executing: ");
        return new ResponseEntity<>(tripConverter
                .convertToInfoDTO(tripService.findAll()), HttpStatus.OK);
    }

    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public Page<TripDetailsDTO> listArticlesPageByPage(@PathVariable("page") int page) {
        PageRequest pageable = PageRequest.of(page - 1, 6);
        Page<Trip> articlePage = tripService.getPaginatedArticles(pageable);
        Page<TripDetailsDTO> postDetailsDTOS = articlePage.map(trip -> tripConverter.convertToDTO(trip));
        return postDetailsDTOS;

    }

    @GetMapping("/{id}")
    public ResponseEntity<TripDetailsDTO> getById(@PathVariable Long id) {
        logger.info("find group get by id method executing: ");
        Trip trip = tripService.findById(id);
        if (trip == null) {
            return null;
        }
        return new ResponseEntity<>(tripConverter
                .convertToDTO(trip), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<TripDetailsDTO> createPost(@RequestBody TripDetailsDTO findGroupDetailsDTO) {
        logger.info("find group create post method executing: ");
        Trip trip = tripService.createFindGroup(tripConverter.convertToEntity(findGroupDetailsDTO));
        return new ResponseEntity<>(tripConverter.convertToDTO(trip), HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        logger.info("delete by id method executing: ");
        tripService.deleteById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<TripDetailsDTO> update(@RequestBody TripDetailsDTO tripDetailsDTO) {
        logger.info("find group update post method executing:  ");
        Trip trip = tripService.update(tripConverter.convertToEntity(tripDetailsDTO));
        return new ResponseEntity<>(tripConverter.convertToDTO(trip), HttpStatus.CREATED);
    }

    @GetMapping("/{tripId}/subscribers")
    public ResponseEntity<List<AccountInfoDTO>> getSubscribers(@PathVariable Long tripId) {
        logger.info("trip getSubscribers method executing: ");
        List<AccountInfoDTO> accountInfoDTOS = accountConverter.convertToInfoDTO(tripService.getSubscribers(tripId));
        return new ResponseEntity<>(accountInfoDTOS, HttpStatus.OK);
    }


    @PutMapping("/{tripId}/subscribe/{accountId}")
    public ResponseEntity<AccountInfoDTO> subscribeAccount(
            @PathVariable(value = "tripId") @NotNull @Positive Long tripId,
            @PathVariable(value = "accountId") @NotNull @Positive Long accountId) {
        logger.info("trip subscribe post method executing:  ");
        //todo add validation
        return new ResponseEntity<>(accountConverter.convertToInfoDTO(tripService.subscribe(tripId, accountId)), HttpStatus.OK);
    }

    @DeleteMapping("/{tripId}/unsubscribe/{accountId}")
    public ResponseEntity unSubscribe(@PathVariable(value = "tripId") @NotNull @Positive Long tripId,
                                      @PathVariable(value = "accountId") @NotNull @Positive Long accountId) {
        Account account = accountService.findById(accountId);
        logger.info("trip unSubscribe post method executing:  ");
        tripService.unSubscribe(tripId, account);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
