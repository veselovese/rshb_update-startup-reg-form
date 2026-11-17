package ru.rshb.intech.digital.references.universities.loader;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenData {

  @JsonProperty("Certificates")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Certificate> certificates;

  @Data
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class Certificate {
    @JsonProperty("Id")
    private String id;
    @JsonProperty("StatusName")
    private String statusName;
    @JsonProperty("EduOrgFullName")
    private String eduOrgFullName;
    @JsonProperty("EduOrgShortName")
    private String eduOrgShortName;
    @JsonProperty("ActualEducationOrganization")
    private ActualEducationOrganization actualEducationOrganization;
    @JsonProperty("Supplements")
    private List<Supplement> supplements;
  }


  @Data
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class ActualEducationOrganization {
    @JsonProperty("Id")
    private String id;
    @JsonProperty("FullName")
    private String fullName;
    @JsonProperty("ShortName")
    private String shortName;
    @JsonProperty("KindName")
    private String kindName;
  }

  @Data
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class Supplement {
    @JsonProperty("Id")
    private String id;
    @JsonProperty("StatusName")
    private String statusName;
    @JsonProperty("StatusCode")
    private String statusCode;
    @JsonProperty("EduOrgFullName")
    private String eduOrgFullName;
    @JsonProperty("EduOrgShortName")
    private String eduOrgShortName;
    @JsonProperty("ActualEducationOrganization")
    private ActualEducationOrganization actualEducationOrganization;
    @JsonProperty("EducationalPrograms")
    private List<EducationalProgram> educationalPrograms;
  }

  @Data
  public static class EducationalProgram {
    @JsonProperty("EduLevelName")
    private String eduLevelName;
    @JsonProperty("IsSuspended")
    private String isSuspended;
    @JsonProperty("IsCanceled")
    private String isCanceled;
  }
}
