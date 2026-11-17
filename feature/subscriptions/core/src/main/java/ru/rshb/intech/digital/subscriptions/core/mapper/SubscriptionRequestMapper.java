package ru.rshb.intech.digital.subscriptions.core.mapper;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.subscriptions.dto.SubscriptionRequest;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionRequestData;

@Component
@RequiredArgsConstructor
public class SubscriptionRequestMapper {

  private final SubscriptionCategoriesMapper categoriesMapper;

  @Nonnull
  public SubscriptionRequestData toEntityFromDto(@Nonnull SubscriptionRequest dto) {

    final var entity = new SubscriptionRequestData();
    entity.setEmail(dto.getEmail());

    final var categories = categoriesMapper.toEntityFromDto(dto.getCategories());
    categories.setPopular(dto.getCategories().isPopular());
    entity.setCategoriesData(categories);

    return entity;
  }
}
