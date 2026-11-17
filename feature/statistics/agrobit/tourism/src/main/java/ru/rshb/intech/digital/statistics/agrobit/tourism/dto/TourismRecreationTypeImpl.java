package ru.rshb.intech.digital.statistics.agrobit.tourism.dto;

import lombok.Getter;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.tourism.TourismRecreationType;
import ru.rshb.intech.digital.statistics.agrobit.tourism.entity.RecreationType;

import jakarta.annotation.Nonnull;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class TourismRecreationTypeImpl implements TourismRecreationType {

  private final String title;
  private final Set<String> categories;

  public TourismRecreationTypeImpl(@Nonnull RecreationType recreationType) {
    this.title = recreationType.getTitle();
    this.categories = recreationType.getCategories()
        .stream()
        .map(category -> category.getTitle())
        .collect(Collectors.toSet());
  }

}
