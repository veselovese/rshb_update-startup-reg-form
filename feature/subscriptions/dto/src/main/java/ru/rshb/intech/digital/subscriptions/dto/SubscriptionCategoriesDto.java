package ru.rshb.intech.digital.subscriptions.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Generated;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

@Data
@Schema(name = "Категории подписки")
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionCategoriesDto {

  @Schema(description = "Темы новостей")
  private List<SubscriptionCategoryDto> news;

  @Schema(description = "Темы событий")
  private List<SubscriptionCategoryDto> events;

  @Schema(description = "Темы подкастов")
  private List<SubscriptionCategoryDto> podcasts;

  @JsonIgnore
  public boolean isEmpty() {
    return CollectionUtils.isEmpty(news)
           && CollectionUtils.isEmpty(events)
           && CollectionUtils.isEmpty(podcasts);
  }
}
