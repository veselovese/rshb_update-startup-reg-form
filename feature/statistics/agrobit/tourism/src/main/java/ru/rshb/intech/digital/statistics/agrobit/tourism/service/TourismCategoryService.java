package ru.rshb.intech.digital.statistics.agrobit.tourism.service;

import ru.rshb.intech.digital.statistics.agrobit.tourism.dto.TourismTypeDto;

import jakarta.annotation.Nonnull;
import java.util.List;

public interface TourismCategoryService {

  @Nonnull
  List<TourismTypeDto> getAllCategories();

}
