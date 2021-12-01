package org.project.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.quarkus.jackson.ObjectMapperCustomizer;

import javax.inject.Singleton;

/**
 * {@link ObjectMapperCustomizer} configuration class
 */

@Singleton
public class CountryObjectMapperCustomizer implements ObjectMapperCustomizer {

    /**
     * Customize the default {@link ObjectMapper} to allow, serialization of empty Java Beans
     *
     * @param objectMapper the mapper instance
     */

    @Override
    public void customize(ObjectMapper objectMapper) {
        // Allow to return empty JSON, for empty Java Beans
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
}
