package org.project.service;

import org.project.model.Country;

public interface CountryService {

    Country getCountryByName(String name);

    Country getCountryById(String id);

    void updateCountry(Country country);

    long removeCountries(String name);

    void removeCountryById(String id);

    String createCountry(Country country);
}
