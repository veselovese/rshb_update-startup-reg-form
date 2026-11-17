package ru.rshb.intech.digital.vacancies.hh.dto.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Schema(name = "Значение фильтра")
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilterValueDto {

  @Schema(description = "ID значения")
  private String id;

  @Schema(description = "Наименование значения")
  private String title;
}
