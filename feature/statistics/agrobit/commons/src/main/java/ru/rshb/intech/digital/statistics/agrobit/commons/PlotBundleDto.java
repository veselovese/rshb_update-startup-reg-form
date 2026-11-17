package ru.rshb.intech.digital.statistics.agrobit.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Schema(name = "Группа графиков")
@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
@RequiredArgsConstructor
public class PlotBundleDto {

  @Schema(description = "Графики")
  private final List<StatsByYearsDto> series;

}
