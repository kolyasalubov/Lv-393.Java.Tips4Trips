package com.softserve.academy.Tips4Trips.controller;

import com.softserve.academy.Tips4Trips.dto.converter.AccountConverter;
import com.softserve.academy.Tips4Trips.dto.converter.TripConverter;
import com.softserve.academy.Tips4Trips.dto.details.TripDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.AccountInfoDTO;
import com.softserve.academy.Tips4Trips.dto.info.TripInfoDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.chat.Chat;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.Trip;
import com.softserve.academy.Tips4Trips.entity.file.Image;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;
import com.softserve.academy.Tips4Trips.exception.FileIOException;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.ChatService;
import com.softserve.academy.Tips4Trips.service.TripService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

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
    private ModelMapper modelMapper;
    private ChatService chatService;

    @Autowired
    public TripController(TripService tripService, TripConverter tripConverter, AccountService accountService, AccountConverter accountConverter, ModelMapper modelMapper, ChatService chatService) {
        this.tripService = tripService;
        this.tripConverter = tripConverter;
        this.accountService = accountService;
        this.accountConverter = accountConverter;
        this.modelMapper = modelMapper;
        this.chatService = chatService;
    }

    @GetMapping
    public ResponseEntity<List<TripInfoDTO>> getAll() {
        logger.info("trips get all method executing: ");
        return new ResponseEntity<>(tripConverter.convertToInfoDTO(
                tripService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/creator/{id}")
    public ResponseEntity<List<TripInfoDTO>> getTripsByCreatorId(@PathVariable Long id) {
        logger.info("create trip method executing: ");
        if (id < 1) {
            return null;
        }
        return new ResponseEntity<>(tripConverter.convertToInfoDTO(
                tripService.findByCreator(accountService.findById(id))), HttpStatus.OK);
    }


    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public Page<TripDetailsDTO> listTripsPageByPage(@PathVariable("page") int page) {
        logger.info("trips page get by creator id method executing: ");
        if (page < 1) {
            return null;
        }
        Page<Trip> articlePage = tripService.getPaginatedArticles(PageRequest.of(page - 1, 6));
        return articlePage.map(trip -> tripConverter.convertToDTO(trip));

    }

    @GetMapping("/{id}")
    public ResponseEntity<TripDetailsDTO> getById(@PathVariable Long id) {
        logger.info("trips get by creator id method executing: ");
        if (id < 1) {
            return null;
        }
        logger.info("trip get by id method executing: ");
        Trip trip = tripService.findById(id);
        return new ResponseEntity<>(tripConverter.convertToDTO(trip), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        logger.info("get trip count method executing: ");
        return new ResponseEntity<>(tripService.getCount(), HttpStatus.OK);
    }


    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<TripDetailsDTO> create(@RequestBody TripDetailsDTO tripDetailsDTO) {
        logger.info("trip create post method executing: ");
        return new ResponseEntity<>(modelMapper.map(
                tripService.createTrip(tripConverter.convertToEntity(tripDetailsDTO)), TripDetailsDTO.class), HttpStatus.CREATED);

    }

    @PostMapping("/{id}/image")
    public ResponseEntity<TripInfoDTO> addImage(@PathVariable Long id,
                                                @RequestParam("file") MultipartFile file)
            throws FileIOException {
        logger.info("trip add image post method executing: ");
        if (id < 1) {
            return null;
        }
        return new ResponseEntity<>(modelMapper.map(
                tripService.createTripImage(file, id), TripInfoDTO.class),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}/image")
    public RedirectView redirectToImageGet(@PathVariable Long id) {
        if (id < 1) {
            return null;
        }
        Image image = tripService.findById(id).getImage();
        Long imageId = image == null ? -1 : image.getId();
        return new RedirectView("/images/" + imageId);
    }

    @DeleteMapping("/{id}/image")
    public void deleteImageById(@PathVariable Long id)
            throws FileIOException, DataNotFoundException {
        logger.info("delete image account by id method executing: ");
        if (id < 1) {
            return;
        }
        tripService.deleteTripImage(id);
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<TripInfoDTO> updateImageById(
            @PathVariable Long id, @RequestParam("file") MultipartFile file)
            throws FileIOException, DataNotFoundException {
        logger.info("update image account by id method executing: ");
        if (id < 1) {
            return null;
        }
        return new ResponseEntity<>(tripConverter
                .convertToDTO(
                        tripService.updateTripImage(id, file)
                ), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteById(@PathVariable Long id) {
        logger.info("trips delete by id method executing: ");
        if (id < 1) {
            return;
        }
        tripService.delete(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<TripDetailsDTO> update(@RequestBody TripDetailsDTO tripDetailsDTO) {
        logger.info("trip update post method executing:  ");
        return new ResponseEntity<>(tripConverter.convertToDTO(
                tripService.update(tripConverter.convertToEntity(tripDetailsDTO))
        ), HttpStatus.OK);
    }

    @GetMapping("/{tripId}/subscribers")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<AccountInfoDTO>> getSubscribers(@PathVariable Long tripId) {
        logger.info("trip getSubscribers method executing: ");
        if (tripId < 1) {
            return null;
        }
        return new ResponseEntity<>(accountConverter.convertToInfoDTO(
                tripService.getSubscribers(tripId)), HttpStatus.OK);
    }


    @PutMapping("/{tripId}/subscribe/{accountId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<AccountInfoDTO> subscribeAccountById(
            @PathVariable(value = "tripId") @NotNull @Positive Long tripId,
            @PathVariable(value = "accountId") @NotNull @Positive Long accountId) {
        logger.info("trip subscribe post method executing:  ");
        return new ResponseEntity<>(accountConverter.convertToInfoDTO(
                tripService.subscribe(tripId, accountId)), HttpStatus.OK);
    }

    @DeleteMapping("/{tripId}/unsubscribe/{accountId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity unSubscribeAccountById(@PathVariable(value = "tripId") @NotNull @Positive Long tripId,
                                                 @PathVariable(value = "accountId") @NotNull @Positive Long accountId) {
        logger.info("trip unSubscribe post method executing:  ");
        tripService.unSubscribe(tripId, accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
