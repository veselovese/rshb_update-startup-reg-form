package ru.rshb.intech.digital.vacancies.hh.dto.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;

@Schema(name = "Значение справочника направлений вакансий")
@Data
@Generated
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecializationFilterValueDto extends FilterValueDto {

  @Schema(description = "ID профессиональной сферы")
  private String profAreaId;

  @Schema(description = "Наименование профессиональной сферы")
  private String profAreaTitle;
}
