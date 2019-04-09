package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.CountryDTO;
import com.softserve.academy.Tips4Trips.dto.converter.reverse.ReverseCountryConverter;
import com.softserve.academy.Tips4Trips.entity.Country;
import com.softserve.academy.Tips4Trips.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends ServiceImpl<Country, Long, CountryRepository>
        implements CountryService {

    ReverseCountryConverter reverseCountryConverter;

    @Autowired
    public CountryService(CountryRepository countryRepository,
                          ReverseCountryConverter reverseCountryConverter) {
        super(countryRepository);
        this.reverseCountryConverter = reverseCountryConverter;
    }

    @Override
    public Country createCountry(CountryDTO countryDTO) {
        Country country = reverseCountryConverter.convert(countryDTO);
        return repository.save(country);
    }
}
