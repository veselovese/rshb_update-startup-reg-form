package ru.rshb.intech.digital.puzzles.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(name = "Запись в таблице рекордов кроссвордов")
@Getter
@Setter
@Builder
@Generated
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrosswordScoresDto {

  @Schema(
      description = "Имя участника",
      example = "Олег Сидоров"
  )
  private String participantName;

  @Schema(
      description = "Результат (время в секундах)",
      example = "253"
  )
  private Long result;

  @Schema(description = "Дата размещения результата")
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "dd.MM.yyyy HH:mm:ss"
  )
  private LocalDateTime submitDate;
}
