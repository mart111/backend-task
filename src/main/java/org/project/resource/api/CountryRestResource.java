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

@Path("/v1/api")
public class CountryRestResource {

    private final CountryService countryService;

    private static final Logger logger = LoggerFactory.getLogger(CountryRestResource.class);

    @Inject
    public CountryRestResource(CountryService countryService) {
        this.countryService = countryService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/country/{id}")
    public Response getCountryById(@PathParam String id) {
        return Response.ok(countryService.getCountryById(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/country")
    public Response getCountryByName(@QueryParam String name) {
        return Response.ok(countryService.getCountryByName(name)).build();
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