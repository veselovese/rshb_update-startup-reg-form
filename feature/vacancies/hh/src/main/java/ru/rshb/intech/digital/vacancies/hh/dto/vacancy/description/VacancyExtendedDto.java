package ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Generated;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VacancyExtendedDto {

  private String id;
  private String description;

  @JsonProperty("branded_description")
  private String brandedDescription;

  @JsonProperty("key_skills")
  private List<SkillDto> keySkills;

  private ScheduleDto schedule;

  @JsonProperty("accept_handicapped")
  private Boolean acceptHandicapped;

  @JsonProperty("accept_kids")
  private Boolean acceptKids;

  private ExperienceDto experience;

  private AddressDto address;

  @JsonProperty("alternate_url")
  private String alternateUrl;

  @JsonProperty("apply_alternate_url")
  private String applyAlternateUrl;

  private String code;

  private DepartmentDto department;

  private EmploymentDto employment;

  private Boolean archived;
  private String name;

  @JsonProperty("insider_interview")
  private InsiderInterviewDto insiderInterview;

  private AreaDto area;

  @JsonProperty("initial_created_at")
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd'T'HH:mm:ssZ"
  )
  private ZonedDateTime initialCreatedAt;

  @JsonProperty("created_at")
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd'T'HH:mm:ssZ"
  )
  private ZonedDateTime createdAt;

  @JsonProperty("published_at")
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd'T'HH:mm:ssZ"
  )
  private ZonedDateTime publishedAt;

  private EmployerDto employer;

  @JsonProperty("response_letter_required")
  private Boolean responseLetterRequired;

  private TypeDto type;

  @JsonProperty("has_test")
  private Boolean hasTest;

  @JsonProperty("response_url")
  private String responseUrl;

  private TestDto test;

  private List<SpecializationDto> specializations;

  @JsonProperty("driver_license_types")
  private List<DriverLicenseTypeDto> driverLicenseTypes;

  @JsonProperty("accept_incomplete_resumes")
  private Boolean acceptIncompleteResumes;

  @JsonProperty("working_days")
  private List<WorkingDayDto> workingDays;

  @JsonProperty("working_time_intervals")
  private List<WorkingTimeIntervalDto> workingTimeIntervals;

  @JsonProperty("working_time_modes")
  private List<WorkingTimeModeDto> workingTimeModes;

  @JsonProperty("accept_temporary")
  private Boolean acceptTemporary;

  @JsonProperty("professional_roles")
  private List<ProfessionalRoleDto> professionalRoles;

  private List<LanguageDto> languages;
}
