package ru.rshb.intech.digital.feature.internship.core.dto.statistics;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.Generated;

import java.util.ArrayList;
import java.util.List;

@Data
@Generated
@Schema(name = "Данные о количестве заявок на стажировку по годам")
public class RegistrationCountDto {

  @Schema(description = "Наименование параметра, отображаемого на графике")
  private String name;

  @Schema(description = "Данные по годам")
  private List<RegistrationCountByYearDto> years;


  public RegistrationCountDto(@Nonnull String name, @Nonnull List<RegistrationCountByYearDto> groups) {
    this.name = name;
    this.years = new ArrayList<>(groups);
  }
}
