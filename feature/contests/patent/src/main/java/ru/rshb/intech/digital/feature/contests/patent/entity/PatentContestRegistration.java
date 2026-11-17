package ru.rshb.intech.digital.feature.contests.patent.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
    schema = "digital_contests",
    name = "patent_fish_regs"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode
public class PatentContestRegistration {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "submit_date",
      nullable = false
  )
  private LocalDateTime submitDate;

  @Column(
      name = "appl_name",
      nullable = false
  )
  private String applicantName;

  @Column(name = "position")
  private String position;

  @Column(name = "org")
  private String organization;

  @Column(
      name = "proj_name",
      nullable = false
  )
  private String projectName;

  @Column(
      name = "proj_stage",
      nullable = false
  )
  private String projectStage;

  @Column(
      name = "pat_reg_num",
      nullable = false
  )
  private String patentNumber;

  @Column(
      name = "pat_reg_year",
      nullable = false
  )
  private Integer patentYear;

  @Column(
      name = "pat_mpk",
      nullable = false
  )
  private String patentMpk;

  @Column(
      name = "pat_holder",
      nullable = false
  )
  private String patentHolder;

  @Column(
      name = "invent_descr_short",
      nullable = false
  )
  private String inventionDescriptionShort;

  @Column(
      name = "mng_subj",
      nullable = false
  )
  private String managingSubject;

  @Column(
      name = "invent_descr_full",
      nullable = false
  )
  private String inventionDescriptionFull;

  @Column(
      name = "email",
      nullable = false
  )
  private String email;

  @Column(
      name = "phone",
      nullable = false
  )
  private String phone;

  @Column(name = "site_url")
  private String siteUrl;

  @Column(
      name = "com_impl",
      nullable = false
  )
  private String commercialImpl;

  @Column(name = "invest_amnt")
  private String investmentsAmount;

  @Column(
      name = "fin_round",
      nullable = false
  )
  private String financingRound;

  @Column(
      name = "clients_amount",
      nullable = false
  )
  private String clientsAmount;

  @Column(name = "staff_size")
  private Integer staffSize;

  @Column(name = "victories")
  private String victoryCases;

  @Column(name = "city_id")
  private String cityId;

  @Column(
      name = "suspicious",
      nullable = false
  )
  private Boolean suspicious = false;
}
