package ru.rshb.intech.digital.statistics.agrobit.salary.dto;

import lombok.Getter;
import ru.rshb.intech.digital.statistics.agrobit.salary.entity.SalaryPositionCategory;
import ru.rshb.intech.digital.statistics.agrobit.sf.client.dto.PositionCategory;

import jakarta.annotation.Nonnull;
import java.util.List;

@Getter
public class PositionCategoryImpl implements PositionCategory {

  private final String categoryName;
  private final List<String> positions;

  public PositionCategoryImpl(@Nonnull SalaryPositionCategory salaryPositionCategory) {
    this.categoryName = salaryPositionCategory.getName();
    this.positions = salaryPositionCategory.getPositions()
        .stream()
        .map(position -> position.getName())
        .toList();
  }
}
