package ru.rshb.intech.digital.puzzles.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Schema(name = "Таблица рекордов для всех кроссвордов")
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrosswordFullScoresTableDto {

  @Schema(description = "Массив таблиц рекордов по кроссвордам")
  private List<CrosswordScoresTableDto> scores;
}
