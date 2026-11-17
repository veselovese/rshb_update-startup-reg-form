package ru.rshb.intech.digital.statistics.agrobit.sf.client.dto;

import jakarta.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public interface PositionCategory {

  PositionCategory ALL = new PositionCategory() {
    @Nonnull
    @Override
    public String getCategoryName() {
      return "Все";
    }

    @Nonnull
    @Override
    public List<String> getPositions() {
      return Collections.emptyList();
    }
  };

  @Nonnull
  String getCategoryName();

  @Nonnull
  List<String> getPositions();

}
