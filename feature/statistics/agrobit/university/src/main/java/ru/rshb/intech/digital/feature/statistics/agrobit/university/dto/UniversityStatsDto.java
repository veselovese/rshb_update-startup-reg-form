package ru.rshb.intech.digital.feature.statistics.agrobit.university.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(name = "Агробит. ВУЗы. Статистика по ВУЗам")
@Generated
@NoArgsConstructor
public class UniversityStatsDto {

  private String name;
  private String shortName;
  private List<FacultyStatsDto> faculties = new ArrayList<>();

  public UniversityStatsDto(String name, String shortName) {
    setName(name);
    setShortName(shortName);
  }
}
