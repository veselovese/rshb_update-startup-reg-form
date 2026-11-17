package ru.rshb.intech.digital.lookups.countries.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        schema = "digital_refs",
        name = "o_plt_oksm_countries"
)
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "code",
            length = 10,
            nullable = false
    )
    private String code;

    @Column(
            name = "short_title",
            length = 100,
            nullable = false,
            unique = true
    )
    private String shortTitle;

    @Column(
            name = "full_title",
            length = 200
    )
    private String fullTitle;

    @Column(
            name = "alpha2",
            length = 2,
            nullable = false
    )
    private String alpha2;

    @Column(
            name = "alpha3",
            length = 3,
            nullable = false
    )
    private String alpha3;
}
