package ru.rshb.intech.digital.feature.internship.core.service.fields;


import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFieldDto;

import java.util.List;

public interface InternshipFieldService {

  @Nonnull
  List<InternshipFieldDto> findAvailableInternshipFields();

  @Nonnull
  List<String> findAvailableFieldTitles();

  @Nonnull
  List<String> findAvailableFieldTitlesIfPresent(@Nonnull List<String> fieldsForCheck);
}
