package com.softserve.academy.Tips4Trips.service.impl;

import com.softserve.academy.Tips4Trips.entity.Country;
import com.softserve.academy.Tips4Trips.repository.CountryRepository;
import com.softserve.academy.Tips4Trips.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl extends ServiceImpl<Country, Long, CountryRepository>
    implements CountryService {

    @Autowired
    public CountryServiceImpl(CountryRepository repository){super(repository);}
}
