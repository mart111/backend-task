package org.project;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.project.model.Country;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class CountryResResourceTest {

    @Test
    public void testPersistEndpoint() {
        Country country = new Country();
        country.setCountryName("France");
        country.setCountryNameAbbr("FRA");


        // make sure that, the country doesn't exist in the table
        given()
                .when().delete("/v1/api/country/delete?name=" + country.getCountryName())
                .then()
                .statusCode(200);


        given()
                .contentType(ContentType.JSON)
                .body(country)
                .when().post("/v1/api/country/persist")
                .then()
                .statusCode(200)
                .body(startsWith("fu:country"));
    }

    @Test
    public void testCountryExistence() {
        given()
                .get("/v1/api/country?name=Bulgaria")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(notNullValue(Country.class));
    }


}