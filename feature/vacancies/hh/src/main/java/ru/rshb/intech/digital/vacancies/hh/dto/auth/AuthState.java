package ru.rshb.intech.digital.vacancies.hh.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Generated;

@Data
@Generated
public class AuthState {

  @JsonProperty("auth_type")
  private String authType;

  @JsonProperty("is_applicant")
  private Boolean isApplicant;

  @JsonProperty("is_employer")
  private Boolean isEmployer;

  @JsonProperty("is_admin")
  private Boolean isAdmin;

  @JsonProperty("is_application")
  private Boolean isApplication;
}
