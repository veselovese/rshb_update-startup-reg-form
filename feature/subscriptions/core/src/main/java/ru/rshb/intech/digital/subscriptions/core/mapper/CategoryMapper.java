package ru.rshb.intech.digital.subscriptions.core.mapper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.collections4.CollectionUtils;
import ru.rshb.intech.digital.subscriptions.dto.SubscriptionCategoryDto;
import ru.rshb.intech.digital.subscriptions.entity.CategoryType;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryMapper {

  @Nullable
  public SubscriptionCategory toEntitiesFromDtos(@Nonnull CategoryType categoryType,
                                                 @Nullable SubscriptionCategoryDto dto) {

    if (dto == null) {
      return null;
    }

    final var entity = new SubscriptionCategory();
    entity.setExternalId(dto.id());
    entity.setTitle(dto.title());
    entity.setType(categoryType);

    return entity;
  }

  @Nonnull
  public List<SubscriptionCategory> toEntitiesFromDtos(@Nonnull CategoryType categoryType,
                                                       @Nullable List<SubscriptionCategoryDto> dtos) {

    if (CollectionUtils.isEmpty(dtos)) {
      return new ArrayList<>();
    }

    return dtos.stream()
        .map(dto -> toEntitiesFromDtos(categoryType, dto))
        .filter(Objects::nonNull)
        .toList();
  }

  @Nonnull
  public SubscriptionCategoryDto toDtoFromEntity(@Nonnull SubscriptionCategory entity) {
    return new SubscriptionCategoryDto(
        entity.getExternalId(),
        entity.getTitle()
    );
  }
}
