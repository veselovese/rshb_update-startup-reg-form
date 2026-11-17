package ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Generated;

@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployerDto {

  private String name;
  private String url;

  @JsonProperty("alternate_url")
  private String alternateUrl;

  private String id;

  private Boolean trusted;

  private Boolean blacklisted;
}
