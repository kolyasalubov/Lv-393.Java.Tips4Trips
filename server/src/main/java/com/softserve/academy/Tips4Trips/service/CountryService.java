package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.CountryDTO;
import com.softserve.academy.Tips4Trips.dto.converter.CountryConverter;
import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.Country;
import com.softserve.academy.Tips4Trips.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    CountryRepository countryRepository;
    CountryConverter countryConverter;

    @Autowired
    public CountryService(CountryRepository countryRepository,
                          CountryConverter countryConverter) {
        this.countryRepository = countryRepository;
        this.countryConverter = countryConverter;
    }

    public Country createCountry(CountryDTO countryDTO) {
        Country country = countryConverter.convertToEntity(countryDTO);
        return countryRepository.save(country);
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public void deleteById(Long id) {
        countryRepository.findById(id).ifPresent(countryRepository::delete);
    }

    public Country update(Country country) {
        if (country.getId() == null) {
            throw new IllegalArgumentException();
        }
        return countryRepository.save(country);
    }

    public Country findById(Long id) {
        return countryRepository.findById(id).get();
    }
}
