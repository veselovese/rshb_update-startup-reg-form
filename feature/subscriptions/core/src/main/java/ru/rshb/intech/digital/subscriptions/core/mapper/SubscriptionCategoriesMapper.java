package ru.rshb.intech.digital.subscriptions.core.mapper;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.subscriptions.dto.SubscriptionCategoriesDto;
import ru.rshb.intech.digital.subscriptions.dto.SubscriptionCategoryDto;
import ru.rshb.intech.digital.subscriptions.entity.CategoryType;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionCategoriesData;
import ru.rshb.intech.digital.subscriptions.entity.SubscriptionCategory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class SubscriptionCategoriesMapper {

  private final CategoryMapper categoryMapper = new CategoryMapper();


  @Nonnull
  public SubscriptionCategoriesData toEntityFromDto(@Nonnull SubscriptionCategoriesDto dto) {

    final var categories = new HashSet<SubscriptionCategory>();
    categories.addAll(categoryMapper.toEntitiesFromDtos(CategoryType.NEWS, dto.getNews()));
    categories.addAll(categoryMapper.toEntitiesFromDtos(CategoryType.EVENTS, dto.getEvents()));
    categories.addAll(categoryMapper.toEntitiesFromDtos(CategoryType.PODCASTS, dto.getPodcasts()));

    return new SubscriptionCategoriesData(categories);
  }

  @Nonnull
  public SubscriptionCategoriesDto toDtoFromEntities(@Nonnull List<SubscriptionCategory> categories) {

    final var newsCategories = new ArrayList<SubscriptionCategoryDto>();
    final var eventsCategories = new ArrayList<SubscriptionCategoryDto>();
    final var podcastsCategories = new ArrayList<SubscriptionCategoryDto>();

    for (final var category : categories) {
      final var categoryDto = categoryMapper.toDtoFromEntity(category);
      switch (category.getType()) {
        case NEWS -> newsCategories.add(categoryDto);
        case EVENTS -> eventsCategories.add(categoryDto);
        case PODCASTS -> podcastsCategories.add(categoryDto);
      }
    }

    final var dto = new SubscriptionCategoriesDto();
    dto.setNews(newsCategories);
    dto.setEvents(eventsCategories);
    dto.setPodcasts(podcastsCategories);
    return dto;
  }
}
