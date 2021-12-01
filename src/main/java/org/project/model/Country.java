package org.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Parameters;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

/**
 * The Entity class for the country beans.
 * Creates a table named 'country'. Custom id generator has
 * been implemented for {@link Country#id} {@link org.project.service.impl.CountryIdGenerator}.
 * The {@link Country#countryNameAbbr} and {@link Country#countryName} are unique.
 *
 *
 */

@Entity
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"country_name", "country_name_abbr"}))
@Data
public class Country extends PanacheEntityBase {

    @Id
    @GenericGenerator(name = "seq_id", strategy = "org.project.service.impl.CountryIdGenerator")
    @GeneratedValue(generator = "seq_id")
    @JsonProperty
    private String id;

    @Column(name = "country_name", nullable = false)
    @JsonProperty
    private String countryName;

    @Column(name = "country_name_abbr")
    @JsonProperty
    private String countryNameAbbr;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    /**
     * Find all the countries by name using Panache find method.
     * @see PanacheEntityBase#find(String, Parameters)
     *
     * @param countryName name of the country
     * @return number of records returned (in ideal case should be 1)
     */
    public static Optional<Country> findByName(String countryName) {
        return find("country_name", countryName).firstResultOptional();
    }


    /**
     * Remove all the countries by name using Panache delete method.
     * @see PanacheEntityBase#delete(String, Parameters)
     *
     * @param countryName name of the country
     * @return number of records removed (in ideal case should be 1)
     */
    @Transactional
    public static long deleteCountryByName(String countryName) {
        return delete("country_name", countryName);
    }


    // EmptyCountry instance will be returned by the API, if the country can't be found
    public static class EmptyCountry {
        private static final EmptyCountry INSTANCE = new EmptyCountry();

        private EmptyCountry() {

        }

        public static EmptyCountry getInstance() {
            return INSTANCE;
        }
    }

}
