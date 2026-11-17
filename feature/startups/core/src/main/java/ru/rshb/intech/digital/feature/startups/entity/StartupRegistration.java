package ru.rshb.intech.digital.feature.startups.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rshb.intech.digital.feature.references.locations.cache.entity.LocationCity;
import ru.rshb.intech.digital.feature.references.locations.cache.entity.LocationCountry;

import java.time.LocalDateTime;

@Entity
@Table(
    schema = "digital_startups",
    name = "reg_showcase"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class StartupRegistration {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "submit_dt")
  private LocalDateTime submitDate;

  @Column(name = "applicant_name")
  private String applicantName;

  @Column(name = "org")
  private String organization;

  private String position;

  private String email;

  private String phone;

  @Column(name = "vk_url")
  private String vkProfileUrl;

  @ManyToOne(
      cascade = {
          CascadeType.MERGE,
          CascadeType.PERSIST
      },
      fetch = FetchType.LAZY
  )
  @JoinColumn(name = "country_id")
  private LocationCountry country;

  @ManyToOne(
      cascade = {
          CascadeType.MERGE,
          CascadeType.PERSIST
      },
      fetch = FetchType.LAZY
  )
  @JoinColumn(name = "city_id")
  private LocationCity city;

  @Column(name = "proj_title")
  private String projectTitle;

  @Column(name = "proj_descr_short")
  private String projectDescriptionShort;

  @Column(name = "proj_descr_full")
  private String projectDescriptionFull;

  @Column(name = "invest_val")
  private Long investmentsValue;

  @ManyToOne(
      cascade = {
          CascadeType.MERGE,
          CascadeType.PERSIST
      },
      fetch = FetchType.LAZY
  )
  @JoinColumn(name = "invest_curr_id")
  private StartupCurrency investmentsCurrency;

  @ManyToOne(
      cascade = {
          CascadeType.MERGE,
          CascadeType.PERSIST
      },
      fetch = FetchType.LAZY
  )
  @JoinColumn(name = "cat_id")
  private StartupCategory category;

  @Column(name = "cat_custom")
  private String categoryCustom;

  @Column(name = "founded_year")
  private Integer foundationYear;

  @Column(name = "staff_size")
  private Integer staffSize;

  @ManyToOne(
      cascade = {
          CascadeType.MERGE,
          CascadeType.PERSIST
      },
      fetch = FetchType.LAZY
  )
  @JoinColumn(name = "proj_stage_id")
  private StartupProjectStage projectStage;

  @ManyToOne(
      cascade = {
          CascadeType.MERGE,
          CascadeType.PERSIST
      },
      fetch = FetchType.LAZY
  )
  @JoinColumn(name = "clients_id")
  private StartupClientsNumberVariant clientsNumberVariant;

  @ManyToOne(
      cascade = {
          CascadeType.MERGE,
          CascadeType.PERSIST
      },
      fetch = FetchType.LAZY
  )
  @JoinColumn(name = "fin_round_id")
  private StartupFinancingRound financingRound;

  private Boolean suspicious = false;
}
