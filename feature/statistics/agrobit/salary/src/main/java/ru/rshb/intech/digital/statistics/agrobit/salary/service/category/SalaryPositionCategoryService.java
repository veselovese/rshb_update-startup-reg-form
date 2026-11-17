package ru.rshb.intech.digital.statistics.agrobit.salary.service.category;

import ru.rshb.intech.digital.statistics.agrobit.salary.dto.category.SalaryPositionCategoryLookupDto;

import jakarta.annotation.Nonnull;

public interface SalaryPositionCategoryService {

  @Nonnull
  SalaryPositionCategoryLookupDto getPositionCategoryLookup();

}
