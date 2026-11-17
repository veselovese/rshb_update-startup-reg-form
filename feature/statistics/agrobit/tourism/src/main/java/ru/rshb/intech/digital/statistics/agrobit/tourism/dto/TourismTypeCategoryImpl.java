package ru.rshb.intech.digital.statistics.agrobit.tourism.dto;

import lombok.Getter;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.tourism.TourismTypeCategory;
import ru.rshb.intech.digital.statistics.agrobit.tourism.entity.TourismType;

import jakarta.annotation.Nonnull;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class TourismTypeCategoryImpl implements TourismTypeCategory {

  private final String typeName;
  private final Set<String> recreationTypes;


  public TourismTypeCategoryImpl(@Nonnull TourismType tourismType) {
    this.typeName = tourismType.getTitle();
    this.recreationTypes = tourismType.getRecreationTypes()
        .stream()
        .map(recreationType -> recreationType.getTitle())
        .collect(Collectors.toSet());
  }
}
