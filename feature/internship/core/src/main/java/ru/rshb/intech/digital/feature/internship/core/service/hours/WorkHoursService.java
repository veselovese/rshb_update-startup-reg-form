package ru.rshb.intech.digital.feature.internship.core.service.hours;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.internship.core.dto.WorkHoursDto;

import java.util.List;

public interface WorkHoursService {

  @Nonnull
  List<WorkHoursDto> findEnabledWorkHours();

  boolean isWorkHoursExist(@Nonnull String title);

  default boolean isWorkHoursNotExist(@Nonnull String title) {
    return !isWorkHoursExist(title);
  }

}
