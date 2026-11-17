package ru.rshb.intech.digital.feature.internship.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Schema(name = "Данные справочника о ступени обучения")
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EducationStageRefDto {

  @Schema(
      description = "ID элемента",
      example = "102fab07-2db3-4fec-9b35-7bb4ba6fe5be"
  )
  private String id;

  @Schema(
      description = "Наименование ступени",
      example = "бакалавриат"
  )
  private String title;

  @Schema(
      description = "Минимальное значение года обучения",
      example = "1"
  )
  private Integer minYear;

  @Schema(
      description = "Максимальное значение года обучения",
      example = "5"
  )
  private Integer maxYear;
}
