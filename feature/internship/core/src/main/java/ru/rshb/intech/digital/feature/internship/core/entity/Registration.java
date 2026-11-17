package ru.rshb.intech.digital.feature.internship.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    schema = "digital_internship",
    name = "o_plt_registration"
)
@Getter
@Setter
@Generated
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Registration implements Cloneable {

  public static final int APPLICANT_NAME_MAX_LEN = 1000;
  public static final int FIELD_MAX_LEN = 1000;
  public static final int PHONE_MAX_LEN = 15;
  public static final int EMAIL_MAX_LEN = 254;
  public static final int ABOUT_MAX_LEN = 1000;
  public static final int LOG_MAX_LEN = 1000;
  public static final int TELEGRAM_MAX_LEN = 300;
  public static final int CITY_MAX_LEN = 300;
  public static final int UNIVERSITY_MAX_LEN = 500;
  public static final int EDU_STAGE_MAX_LEN = 100;
  public static final int SKILLS_MAX_LEN = 1000;
  public static final int DESIRED_TASKS_MAX_LEN = 1000;
  public static final int PORTFOLIO_MAX_LEN = 1000;
  public static final int EXPERIENCE_MAX_LEN = 1000;
  public static final int PREF_HOURS_LEN = 10;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
      name = "applicant_name",
      nullable = false,
      length = APPLICANT_NAME_MAX_LEN
  )
  private String applicantName;

  @Column(
      name = "internship_field",
      nullable = false,
      length = FIELD_MAX_LEN
  )
  private String internshipField;

  @Column(
      name = "phone",
      nullable = false,
      length = PHONE_MAX_LEN
  )
  private String phone;

  @Column(
      name = "email",
      nullable = false,
      length = EMAIL_MAX_LEN
  )
  private String email;

  @Column(
      name = "about",
      length = ABOUT_MAX_LEN
  )
  private String about;


  @Column(name = "registration_date")
  private LocalDateTime registrationDate;

  @Column(
      name = "log",
      nullable = false,
      length = LOG_MAX_LEN
  )
  private String log;

  @Column(
      name = "telegram",
      length = TELEGRAM_MAX_LEN
  )
  private String telegram;

  @Column(
      name = "birth_date",
      nullable = false
  )
  private LocalDate birthDate;

  @Column(
      name = "city_id",
      length = CITY_MAX_LEN
  )
  private String cityId;

  @Column(
      name = "city_custom",
      length = CITY_MAX_LEN
  )
  private String cityCustom;

  @Column(
      name = "university_id",
      length = UNIVERSITY_MAX_LEN
  )
  private String universityId;

  @Column(
      name = "university_custom",
      length = UNIVERSITY_MAX_LEN
  )
  private String universityCustom;

  @Column(
      name = "edu_stage",
      length = EDU_STAGE_MAX_LEN
  )
  private String educationStage;

  @Column(name = "edu_year")
  private Integer educationYear;

  @Column(
      name = "skills",
      length = SKILLS_MAX_LEN
  )
  private String aboutSkills;

  @Column(
      name = "desired_tasks",
      length = DESIRED_TASKS_MAX_LEN
  )
  private String aboutWishedTasks;

  @Column(
      name = "portfolio",
      length = PORTFOLIO_MAX_LEN
  )
  private String aboutPortfolio;

  @Column(
      name = "experience",
      length = EXPERIENCE_MAX_LEN
  )
  private String aboutExperience;

  @Column(name = "pref_hrs_wk")
  private String workHours;

  @Column(
      name = "suspicious"
  )
  private Boolean suspicious;

  public boolean isSuspicious() {
    return (suspicious != null && suspicious);
  }

  @Override
  public Registration clone() {
    return new Registration(
        id,
        applicantName,
        internshipField,
        phone,
        email,
        about,
        registrationDate,
        log,
        telegram,
        birthDate,
        cityId,
        cityCustom,
        universityId,
        universityCustom,
        educationStage,
        educationYear,
        aboutSkills,
        aboutWishedTasks,
        aboutPortfolio,
        aboutExperience,
        workHours,
        suspicious
    );
  }
}
