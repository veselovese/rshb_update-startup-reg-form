package ru.rshb.intech.digital.feature.startups.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

  private String email;

  private String phone;

  @Column(name = "city_name")
  private String city;

  private String inn;

  @Column(name = "website_url")
  private String websiteUrl;

  @Column(name = "investor_name")
  private String investorName;

  @Column(name = "proj_title")
  private String projectTitle;

  @Column(name = "proj_descr_short")
  private String projectDescriptionShort;

  @Column(name = "proj_descr_full")
  private String projectDescriptionFull;

  @Column(name = "invest_val")
  private Long investmentsValue;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      schema = "digital_startups",
      name = "reg_showcase_categories",
      joinColumns = @JoinColumn(name = "registration_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private List<StartupCategory> categories = new ArrayList<>();

  @Column(name = "founded_year")
  private Integer foundationYear;

  @ManyToOne(
      cascade = {
          CascadeType.MERGE,
          CascadeType.PERSIST
      },
      fetch = FetchType.LAZY
  )
  @JoinColumn(name = "proj_stage_id")
  private StartupProjectStage projectStage;

  @Column(name = "revenue_range")
  private String revenueRange;

  @Column(name = "has_ai_solutions")
  private Boolean hasAiSolutions;

  @Column(name = "video_url")
  private String videoUrl;

  private Boolean suspicious = false;
}
