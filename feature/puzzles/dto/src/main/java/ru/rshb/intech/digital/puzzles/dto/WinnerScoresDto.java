package ru.rshb.intech.digital.puzzles.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(name = "Результат отгадывания кроссворда")
@Getter
@Setter
@Generated
@EqualsAndHashCode
@NoArgsConstructor
public class WinnerScoresDto {

  @Schema(description = "Время, за которое был отгадан кроссворд в секундах")
  @NotNull(message = "{puzzle.crossword.error.validation.scores.result.missing}")
  @Positive(message = "{puzzle.crossword.error.validation.scores.result.invalid}")
  private Long result;

  @Schema(description = "Имя участника, отгадавшего кроссворд")
  @NotBlank(message = "{puzzle.crossword.error.validation.scores.participant.name.missing}")
  private String participantName;
}
