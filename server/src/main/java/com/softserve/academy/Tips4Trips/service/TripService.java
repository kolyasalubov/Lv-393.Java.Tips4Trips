package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.Route;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.entertainment.mountains.Trip;
import com.softserve.academy.Tips4Trips.repository.TripRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    private static final Logger logger = Logger.getLogger(TripService.class);

    @PersistenceContext
    private EntityManager em;

    TripRepository repository;

    @Autowired
    public TripService(TripRepository repository) {
        this.repository = repository;
    }

    public List<Trip> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public List<Trip> findByCreator(Account author) {
        return repository.findByCreator(author);
    }

    public Trip findById(Long id) {
        Optional<Trip> i =repository.findById(id);
        if(i.isPresent()){
            return i.get();
        }
        else {
            return null;
        }
    }

    @Transactional
    public void subscribe(Long tripId, Account account){
         em.createNativeQuery("INSERT  INTO  subscriber_group(subscriber_id,group_id)  values(?,?)")
                 .setParameter(1,account.getId())
                 .setParameter(2,tripId)
                 .executeUpdate();
    }


    @Transactional
    public void unSubscribe(Long tripId, Account account){
        em.createNativeQuery("DELETE FROM  subscriber_group where subscriber_id =?  and group_id = ?")
                .setParameter(1,account.getId())
                .setParameter(2,tripId)
                .executeUpdate();
    }
    @Transactional
    public List<Account> getSubscribers(Long tripId){
        Trip trip = repository.findById(tripId).get();
        return trip.getSubscribers();
        //return  em.createQuery("select subscribers from Trip ").getResultList();


    }


    public void delete(Trip trip) {
        repository.delete(trip);
    }

    public List<Trip> findByRoute(Route route) {
        return repository.findByRoute(route);
    }

    public List<Trip> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

    public Trip createFindGroup(Trip trip) {
        return repository.save(trip);
    }

    public Trip update(Trip trip) {
        return repository.save(trip);
    }

    public Page<Trip> getPaginatedArticles(Pageable pageable) {
        return repository.findAllByOrderByIdDesc(pageable);
    }
}
