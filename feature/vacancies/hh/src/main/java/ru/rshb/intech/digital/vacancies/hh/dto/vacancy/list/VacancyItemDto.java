package ru.rshb.intech.digital.vacancies.hh.dto.vacancy.list;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;

@Data
@Generated
@EqualsAndHashCode(of = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VacancyItemDto {

  private String id;
  private String name;
}
