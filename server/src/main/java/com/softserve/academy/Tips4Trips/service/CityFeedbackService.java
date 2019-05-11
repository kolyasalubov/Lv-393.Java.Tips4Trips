package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.city.CityFeedback;
import com.softserve.academy.Tips4Trips.repository.CityFeedbackRepository;
import com.softserve.academy.Tips4Trips.security.UserDetailsImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityFeedbackService {

    private static final Logger logger = Logger.getLogger(CityService.class);

    CityFeedbackRepository cityFeedbackRepository;

    @Autowired
    public CityFeedbackService(CityFeedbackRepository cityFeedbackRepository) {
        this.cityFeedbackRepository = cityFeedbackRepository;
    }

    public CityFeedback createCityFeedback(CityFeedback cityFeedback) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        cityFeedback.setCreatedBy(principal.getUsername());
        return cityFeedbackRepository.save(cityFeedback);
    }

    public CityFeedback update(CityFeedback cityFeedback) {
        if (cityFeedback.getId() == null) {
            throw new IllegalArgumentException();
        }
        return cityFeedbackRepository.save(cityFeedback);
    }

    public void deleteById(Long id) {
        cityFeedbackRepository.findById(id).ifPresent(cityFeedbackRepository::delete);
    }

    public List<CityFeedback> findByCityFeedback(Long id) {
        return cityFeedbackRepository.findByCityId(id);
    }
}
