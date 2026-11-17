package ru.rshb.intech.digital.feature.contests.chef.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Schema(name = "Результат проверки URL для конкурса \"Повар\"")
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class ChefContestUrlCheckResult {

  @Schema(description = "Кол-во правильных ответов")
  private Long level;
}
