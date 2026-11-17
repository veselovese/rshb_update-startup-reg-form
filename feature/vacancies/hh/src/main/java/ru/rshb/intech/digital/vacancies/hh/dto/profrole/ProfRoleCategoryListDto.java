package ru.rshb.intech.digital.vacancies.hh.dto.profrole;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfRoleCategoryListDto {

  private List<ProfRoleCategoryDto> categories;

}
