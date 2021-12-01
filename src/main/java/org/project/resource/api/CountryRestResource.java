package org.project.resource.api;

import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;
import org.project.model.Country;
import org.project.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

/**
 * The CountryRestResource
 */

@Path("/v1/api")
public class CountryRestResource {

    private final CountryService countryService;

    private static final Logger logger = LoggerFactory.getLogger(CountryRestResource.class);

    @Inject
    public CountryRestResource(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * Returns the JSON representation of the Country instance or empty JSON,
     * if the country is null
     *
     * @param id the id of the country
     * @return JSON representation of the {@link Country} or {@link org.project.model.Country.EmptyCountry} instance
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/country/{id}")
    public Response getCountryById(@PathParam String id) {
        Country country = countryService.getCountryById(id);
        if (Objects.nonNull(country))
            return Response.ok(country).build();
        else return Response.ok(Country.EmptyCountry.getInstance()).build();
    }

    /**
     * Returns the JSON representation of the Country instance or empty JSON,
     * if the country is null
     *
     * @param name name of the country
     * @return JSON representation of the {@link Country} or {@link org.project.model.Country.EmptyCountry} instance
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/country")
    public Response getCountryByName(@QueryParam String name) {
        Country country = countryService.getCountryByName(name);
        if (Objects.nonNull(country))
            return Response.ok(country).build();
        else return Response.ok(Country.EmptyCountry.getInstance()).build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/country/persist")
    public Response persistCountry(Country country) {
        try {
            String id = countryService.createCountry(country);
            logger.info("Country {}, has been successfully persisted.", country);
            return Response.ok(id)
                    .build();
        } catch (Exception e) {
            logger.error("Failed to persist country. Message from server: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Exception Message: " + e.getMessage())
                    .build();
        }
    }

    /**
     * Updates the country. Consumes JSON representation of the {@link Country} object.
     * The correct {@link Country#id} should be provided.
     *
     * @param country country object to be updated
     * @return <i>Country Updated Successfully</i> if operation succeeded, otherwise <i>Failed to update country</i>
     */
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/country/update")
    public Response updateCountry(Country country) {
        try {
            countryService.updateCountry(country);
            logger.info("Country {}, has been successfully updated", country);
            return Response.ok("Country Updated Successfully")
                    .build();
        } catch (Exception e) {
            logger.error("Failed to update country. Message from server: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Exception Message: " + e.getMessage())
                    .build();
        }
    }

    /**
     * Remove country by id
     *
     * @param id the id of the country
     * @return This method always returned normally, unless exception throws
     */
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/country/delete/{id}")
    public Response removeCountry(@PathParam String id) {
        try {
            countryService.removeCountryById(id);
            logger.info("Country with id {}, has been successfully removed", id);
            return Response.ok("Country has been removed successfully")
                    .build();
        } catch (Exception e) {
            logger.error("Error occurred, while removing country with ID {}. Exception Message: {}", id, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Exception Message: " + e.getMessage())
                    .build();
        }
    }


    /**
     * Remove country by name
     *
     * @param name the name of the country
     * @return This method always returned normally, unless exception throws
     */
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/country/delete")
    public Response removeCountryWithName(@QueryParam String name) {
        try {
            long removalCount = countryService.removeCountries(name);
            logger.info("{} items have been removed with name {}", removalCount, name);
            return Response.ok("Country has been removed successfully")
                    .build();
        } catch (Exception e) {
            logger.error("Error occurred, while removing country with name {}. Exception Message: {}", name, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Exception Message: " + e.getMessage())
                    .build();
        }

    }
}