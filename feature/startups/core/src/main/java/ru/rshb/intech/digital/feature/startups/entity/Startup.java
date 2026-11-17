package ru.rshb.intech.digital.feature.startups.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    schema = "digital_startups",
    name = "startups_showcase"
)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Startup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "ext_id",
      updatable = false,
      nullable = false,
      unique = true
  )
  private String externalId;

  @Column(
      name = "title",
      nullable = false
  )
  private String title;

  private String summary;

  @Column(name = "descr")
  private String description;

  @Column(name = "site_url")
  private String siteUrl;

  @Column(name = "workers_num")
  private Long workersNum = 0L;

  @Column(name = "founded_yr")
  private Integer foundationYear = 0;

  @Column(name = "logo_url")
  private String logoUrl;

  @Column(name = "round")
  private String round;

  @Column(name = "clients_num")
  private String clientsNum;

  private String stage;

  @Column(name = "cat")
  private String category;

  private String country;

  @Column(name = "is_archived")
  private boolean inArchive = false;

  private String district;

  private String region;

  @Column(name = "invest_amnt")
  private Long investmentsAmount;

  @Column(name = "invest_curr")
  private String investmentsCurrency;

  @Column(name = "hidden_info")
  private String hiddenInfo;

  @Column(name = "opt_file_url")
  private String optFileUrl;
}
