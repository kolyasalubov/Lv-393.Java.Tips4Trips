package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.constants.ExceptionMessages;
import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.Trip;
import com.softserve.academy.Tips4Trips.entity.file.Image;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;
import com.softserve.academy.Tips4Trips.exception.DuplicateValueException;
import com.softserve.academy.Tips4Trips.exception.FileIOException;
import com.softserve.academy.Tips4Trips.repository.TripRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TripService {

    private static final Logger logger = Logger.getLogger(TripService.class);

    @PersistenceContext
    private EntityManager em;

    TripRepository repository;
    AccountService accountService;
    private FileStorageService fileStorageService;
    @Autowired
    public TripService(TripRepository repository, AccountService accountService, FileStorageService fileStorageService) {
        this.repository = repository;
        this.accountService = accountService;
        this.fileStorageService = fileStorageService;
    }

    public List<Trip> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public List<Trip> findByCreator(Account author) {
        return repository.findByCreator(author);
    }

    public Trip findById(Long tripId) {
        Optional<Trip> i = repository.findById(tripId);
        if (i.isPresent()) {
            return i.get();
        } else {
            throw new DataNotFoundException(ExceptionMessages.TRIP_BY_THIS_ID_IS_NOT_FOUND);
        }
    }

    public Trip createTripImage(MultipartFile image, Long id)
            throws FileIOException {
        Trip trip = findById(id);
        if (trip.getImage() != null) {
            throw new FileIOException("Trip image already exists! Try " +
                    "updating it.");
        }
        Image newImage = fileStorageService.store(image);
        trip.setImage(newImage);
        return update(trip);
    }

    public void deleteTripImage(Long id) throws FileIOException,
            DataNotFoundException {
        try {
            Trip trip = findById(id);
            Long imageId = trip.getImage().getId();
            trip.setImage(null);
            update(trip);
            fileStorageService.deleteFile(imageId);
        } catch (NullPointerException e) {
            throw new DataNotFoundException("Image doesn't exist");
        }
    }

    public Trip updateTripImage(Long id, MultipartFile newImage)
            throws FileIOException, DataNotFoundException {
        deleteTripImage(id);
        Image image = fileStorageService.store(newImage);
        Trip trip = findById(id);
        trip.setImage(image);
        return update(trip);
    }


    @Transactional
    public Account subscribe(Long tripId, Long accountId) {
        Optional<Trip> trip = repository.findById(tripId);

        if (trip.isPresent()) {
            Account account = accountService.findById(accountId);
            if (!trip.get().getSubscribers().contains(account)) {
                trip.get().addSubscriber(account);
                return account;
            } else {
                throw new DuplicateValueException(ExceptionMessages.ACCOUNT_CAN_NOT_BE_SUBSCRIBED_TWICE);
            }
        } else {
            throw new DataNotFoundException(ExceptionMessages.TRIP_BY_THIS_ID_IS_NOT_FOUND);
        }
    }

    @Transactional
    public void unSubscribe(Long tripId, Long accountId) {
        Optional<Trip> trip = repository.findById(tripId);
        if (trip.isPresent()) {
            trip.get().removeSubscriber(accountService.findById(accountId));
        } else {
            throw new DataNotFoundException(ExceptionMessages.TRIP_BY_THIS_ID_IS_NOT_FOUND);
        }
    }

    @Transactional
    public List<Account> getSubscribers(Long tripId) {
        Optional<Trip> trip = repository.findById(tripId);
        if (trip.isPresent()) {
            List<Account> accountList = trip.get().getSubscribers();
            Collections.reverse(accountList);
            return accountList;
        } else {
            throw new DataNotFoundException(ExceptionMessages.TRIP_BY_THIS_ID_IS_NOT_FOUND);
        }
    }


    public void delete(Trip trip) {

        Optional<Trip> tripOptional = repository.findById(trip.getId());

        if (tripOptional.isPresent()) {
            repository.delete(trip);
        } else {
            throw new DataNotFoundException(ExceptionMessages.TRIP_BY_THIS_ID_IS_NOT_FOUND);
        }

        repository.delete(trip);
    }

    public List<Trip> findByRoute(Route route) {
        return repository.findByRoute(route);
    }

    public List<Trip> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        Optional<Trip> tripOptional = repository.findById(id);

        if (tripOptional.isPresent()) {
            repository.delete(tripOptional.get());
        } else {
            throw new DataNotFoundException(ExceptionMessages.TRIP_BY_THIS_ID_IS_NOT_FOUND);
        }
    }

    public Trip createTrip(Trip trip) {
        return repository.save(trip);
    }

    public Trip update(Trip trip) {
        return repository.save(trip);
    }

    public Page<Trip> getPaginatedArticles(Pageable pageable) {
        return repository.findAllByOrderByIdDesc(pageable);
    }

    public Long getCount() {
        return repository.count();
    }
}
