package ru.rshb.intech.digital.vacancies.hh.dto.profrole;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Generated;

@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfRoleDto {

  private String id;
  private String name;

}
