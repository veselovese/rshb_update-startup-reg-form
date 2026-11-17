package ru.rshb.intech.digital.subscriptions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import org.apache.commons.lang3.BooleanUtils;

@Schema(name = "Категории подписки при оформлении")
@Data
@Generated
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionCategoriesRequest extends SubscriptionCategoriesDto {

  @Schema(description = "Популярные категории")
  private Boolean popular;

  public boolean isPopular() {
    return BooleanUtils.toBooleanDefaultIfNull(popular, false);
  }
}
