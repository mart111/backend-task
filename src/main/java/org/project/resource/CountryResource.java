package org.project.resource;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.i18n.MessageBundles;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;
import org.project.CountryUtil;
import org.project.model.Country;
import org.project.service.CountryRestService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/country")
public class CountryResource {

    @Inject
    Template home;

    @RestClient
    @Inject
    CountryRestService countryRestService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/{lang}/{id}")
    public TemplateInstance getCountryById(@PathParam String lang, @PathParam String id) {
        Country country = countryRestService.getCountryById(id);
        String countryName = CountryUtil.resolveLocalizedCountryName(country.getCountryNameAbbr(), lang);
        return home.instance()
                .setAttribute(MessageBundles.ATTRIBUTE_LOCALE, lang)
                .data("id", country.getId())
                .data("name", countryName)
                .data("createdAt", country.getCreatedAt())
                .data("updatedAt", country.getUpdatedAt());
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/{lang}")
    public TemplateInstance getCountryByName(@PathParam String lang, @QueryParam String name) {
        Country country = countryRestService.getCountryByName(name);
        String countryName = CountryUtil.resolveLocalizedCountryName(country.getCountryNameAbbr(), lang);
        return home.instance()
                .setAttribute(MessageBundles.ATTRIBUTE_LOCALE, lang)
                .data("id", country.getId())
                .data("name", countryName)
                .data("createdAt", country.getCreatedAt())
                .data("updatedAt", country.getUpdatedAt());
    }
}
