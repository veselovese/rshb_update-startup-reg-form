package ru.rshb.intech.digital.feature.internship.core.dto.statistics;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Schema(name = "Данные о количестве заявок за год по месяцам")
@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class RegistrationCountByYearDto {

  private int year;

  @Schema(description = "Данные по месяцам")
  private List<RegistrationCountByMonthDto> months;
}
