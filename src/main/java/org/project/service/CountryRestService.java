package org.project.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;
import org.project.model.Country;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/v1/api")
@RegisterRestClient(configKey = "country-api")
public interface CountryRestService {

    @GET
    @Path("/country/{id}")
    Country getCountryById(@PathParam String id);

    @GET
    @Path("/country")
    Country getCountryByName(@QueryParam String name);
}
