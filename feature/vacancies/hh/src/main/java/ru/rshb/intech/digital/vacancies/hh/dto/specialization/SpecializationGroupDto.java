package ru.rshb.intech.digital.vacancies.hh.dto.specialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecializationGroupDto {

  private String id;
  private String name;
  private List<SpecializationDto> specializations;
}
