package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.dto.CountryDTO;
import com.softserve.academy.Tips4Trips.entity.Country;
import com.softserve.academy.Tips4Trips.entity.Post;
import com.softserve.academy.Tips4Trips.repository.CountryRepository;

public interface CountryService extends Service<Country, Long, CountryRepository> {

    Country createCountry(CountryDTO countryDTO);
}
