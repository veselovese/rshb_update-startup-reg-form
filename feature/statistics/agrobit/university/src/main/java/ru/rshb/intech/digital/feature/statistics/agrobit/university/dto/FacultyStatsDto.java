package ru.rshb.intech.digital.feature.statistics.agrobit.university.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(name = "Агробит. ВУЗы. Статистика по факультетам")
@Generated
@NoArgsConstructor
public class FacultyStatsDto {

  private String name;
  private List<YearStatItemDto> data = new ArrayList<>();

  public FacultyStatsDto(@Nonnull String name) {
    this.name = name;
  }
}
