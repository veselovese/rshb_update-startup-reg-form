package ru.rshb.intech.digital.statistics.agrobit.salary.service.category;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.statistics.agrobit.salary.dto.category.SalaryPositionCategoryDto;
import ru.rshb.intech.digital.statistics.agrobit.salary.dto.category.SalaryPositionCategoryLookupDto;
import ru.rshb.intech.digital.statistics.agrobit.salary.dto.category.SalaryPositionDto;
import ru.rshb.intech.digital.statistics.agrobit.salary.entity.SalaryPosition;
import ru.rshb.intech.digital.statistics.agrobit.salary.entity.SalaryPositionCategory;
import ru.rshb.intech.digital.statistics.agrobit.salary.repository.SalaryPositionCategoryRepository;

@Service
@RequiredArgsConstructor
public class SalaryPositionCategoryServiceImpl implements SalaryPositionCategoryService {

  private final SalaryPositionCategoryRepository salaryPositionCategoryRepository;

  @Nonnull
  @Override
  public SalaryPositionCategoryLookupDto getPositionCategoryLookup() {
    final var categories = salaryPositionCategoryRepository.findAllByEnabledTrueOrderByNameAsc();
    return new SalaryPositionCategoryLookupDto(
        categories.stream()
            .map(category -> map(category))
            .toList()
    );
  }

  @Nonnull
  private SalaryPositionCategoryDto map(@Nonnull SalaryPositionCategory category) {
    final var dto = new SalaryPositionCategoryDto();
    dto.setExternalId(category.getExternalId());
    dto.setTitle(category.getName());
    dto.setPositions(
        category.getPositions()
            .stream()
            .filter(position -> position.getEnabled())
            .map(position -> map(position))
            .toList()
    );
    return dto;
  }

  @Nonnull
  private SalaryPositionDto map(@Nonnull SalaryPosition position) {
    final var dto = new SalaryPositionDto();
    dto.setExternalId(position.getExternalId());
    dto.setTitle(position.getName());
    return dto;
  }
}
