package ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.tourism;

import jakarta.annotation.Nonnull;
import java.util.Collections;
import java.util.Set;

public interface TourismTypeCategory {

  TourismTypeCategory ALL = new TourismTypeCategory() {
    @Nonnull
    @Override
    public String getTypeName() {
      return "Все";
    }

    @Nonnull
    @Override
    public Set<String> getRecreationTypes() {
      return Collections.emptySet();
    }
  };

  @Nonnull
  String getTypeName();

  @Nonnull
  Set<String> getRecreationTypes();


}
