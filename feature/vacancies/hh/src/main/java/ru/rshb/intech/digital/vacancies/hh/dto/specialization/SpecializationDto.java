package ru.rshb.intech.digital.vacancies.hh.dto.specialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Generated;

@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecializationDto {

  private String id;
  private String name;
}
