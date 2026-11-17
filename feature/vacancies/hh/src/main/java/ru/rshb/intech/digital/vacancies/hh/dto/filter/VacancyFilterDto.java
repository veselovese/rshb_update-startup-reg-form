package ru.rshb.intech.digital.vacancies.hh.dto.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(name = "Фильтр вакансий")
@Data
@Builder
@Generated
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VacancyFilterDto {

  @Schema(
      description = "ID/ключ фильтра",
      example = "sort"
  )
  private String id;

  @Schema(
      description = "Локализованное наименование фильтра для вывода в UI.",
      example = "Сортировка"
  )
  private String title;

  @Schema(description = "Допустимые значения фильтра")
  private List<? extends FilterValueDto> values;

  @Schema(
      description = "Тип выбора значения фильтра",
      example = "single|multiple"
  )
  private CriteriaType type;
}
