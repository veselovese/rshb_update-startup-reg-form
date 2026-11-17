package ru.rshb.intech.digital.vacancies.hh.dto.vacancy.description;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Generated;

@Data
@Generated
public class SpecializationDto {

  private String id;
  private String name;

  @JsonProperty("profarea_id")
  private String profAreaId;

  @JsonProperty("profarea_name")
  private String profAreaName;
}
