package ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.tourism;

import jakarta.annotation.Nonnull;
import java.util.Set;

public interface TourismRecreationType {

  @Nonnull
  String getTitle();

  @Nonnull
  Set<String> getCategories();

}
