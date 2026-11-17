package ru.rshb.intech.digital.subscriptions.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Schema(name = "Обратная связь от пользователя о причинах отписки от рассылки")
@Data
@Generated
public class UnsubscribeFeedbackDto {

  @Schema(description = "Фиксированные варианты причин отписки")
  private List<String> reasons;

  @Schema(description = "Обратная связь в свободной форме")
  private String feedback;
}
