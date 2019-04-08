package com.softserve.academy.Tips4Trips.service.impl;

import com.softserve.academy.Tips4Trips.dto.CountryDTO;
import com.softserve.academy.Tips4Trips.dto.converter.reverse.ReverseCountryConverter;
import com.softserve.academy.Tips4Trips.entity.Country;
import com.softserve.academy.Tips4Trips.repository.CountryRepository;
import com.softserve.academy.Tips4Trips.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl extends ServiceImpl<Country, Long, CountryRepository>
        implements CountryService {

    ReverseCountryConverter reverseCountryConverter;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository,
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
