package ru.rshb.intech.digital.feature.contests.chef.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Generated;

@Schema(name = "Данные для проверки по проекту \"Повар\"")
@Data
@Generated
public class ChefContestUrlCheckRequest {

  @Schema(description = "URL проверяемого API.")
  private String url;
}
