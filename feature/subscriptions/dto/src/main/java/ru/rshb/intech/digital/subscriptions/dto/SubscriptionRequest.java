package ru.rshb.intech.digital.subscriptions.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Generated;
import ru.rshb.intech.digital.subscriptions.dto.validation.SubscriptionCategory;

@Schema(name = "Данные подписки")
@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionRequest {

  @Schema(
      description = "Адрес электронной почты подписчика.",
      example = "none@none.com"
  )
  @NotBlank(message = "{common.error.validation.email.missing}")
  @Email(message = "{common.error.validation.email.format}")
  private String email;

  @Schema(description = "Список категорий подписки.")
  @SubscriptionCategory(message = "{subscriptions.error.validation.categories.missing}")
  private SubscriptionCategoriesRequest categories;
}
