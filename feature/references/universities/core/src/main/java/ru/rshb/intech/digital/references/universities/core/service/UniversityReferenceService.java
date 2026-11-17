package ru.rshb.intech.digital.references.universities.core.service;

import ru.rshb.intech.digital.references.universities.dto.UniversityDto;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.List;

public interface UniversityReferenceService {

  @Nonnull
  List<UniversityDto> findByName(@Nonnull String nameTemplate);

  @Nonnull
  String findUniversityFullNameByIdOrEmpty(@Nullable String universityId);
}
