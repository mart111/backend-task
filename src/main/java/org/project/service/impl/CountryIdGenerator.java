package org.project.service.impl;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.project.model.Country;

import java.io.Serializable;

/**
 * Hibernate custom Identifier generator.
 * Following the format <b>fu:country:147</b>
 * The trailing random number was generated using {@link Math#random()}
 *
 */
public class CountryIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        if (o instanceof Country) {
            String id = "fu:country:";
            String trail = String.valueOf(Math.random()).replace("0.", "");
            return id.concat(trail);
        }
        return null;
    }
}
