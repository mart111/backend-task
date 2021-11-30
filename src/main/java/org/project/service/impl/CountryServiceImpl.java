package org.project.service.impl;

import org.hibernate.Session;
import org.project.model.Country;
import org.project.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@ApplicationScoped
public class CountryServiceImpl implements CountryService {

    private static final Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);

    private final Session session;

    @Inject
    public CountryServiceImpl(Session session) {
        this.session = session;
    }

    @Override
    public Country getCountryByName(String name) {
        Optional<Country> country = Country.findByName(name);
        return country.orElse(null);
    }

    @Override
    public Country getCountryById(String id) {
        return session.get(Country.class, id);
    }

    @Override
    @Transactional
    public void updateCountry(Country country) {
        country.setUpdatedAt(new Date());
        session.update(country);
    }

    @Override
    public long removeCountries(String name) {
        return Country.deleteCountryByName(name);
    }

    @Override
    @Transactional
    public void removeCountryById(String id) {
        session.load(Country.class, id)
                .delete();
    }

    @Override
    @Transactional
    public String createCountry(Country country) {
        Date now = new Date();
        country.setCreatedAt(now);
        country.setUpdatedAt(now);
        return (String) session.save(country);
    }
}
