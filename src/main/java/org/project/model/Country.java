package org.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

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

    public static Optional<Country> findByName(String countryName) {
        return find("country_name", countryName).firstResultOptional();
    }

    @Transactional
    public static long deleteCountryByName(String countryName) {
        return delete("country_name", countryName);
    }
}
