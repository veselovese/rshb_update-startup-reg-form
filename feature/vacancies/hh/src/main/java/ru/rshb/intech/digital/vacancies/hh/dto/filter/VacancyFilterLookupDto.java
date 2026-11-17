package ru.rshb.intech.digital.vacancies.hh.dto.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(name = "Справочник фильтров вакансий")
@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VacancyFilterLookupDto {

  private List<VacancyFilterDto> filters;

}
