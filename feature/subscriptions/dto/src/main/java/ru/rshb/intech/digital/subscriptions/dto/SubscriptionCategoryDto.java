package ru.rshb.intech.digital.subscriptions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import lombok.Generated;

@Schema(name = "Категория подписки")
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public record SubscriptionCategoryDto(

    @Schema(
        description = "ID категории.",
        example = "8068a293-1b93-41d6-bdbd-2cf38d63ea9f"
    )
    String id,

    @Schema(
        description = "Наименование категории",
        example = "Бизнес"
    )
    String title
) {

  public SubscriptionCategoryDto(@Nonnull String title) {
    this(null, title);
  }
}
