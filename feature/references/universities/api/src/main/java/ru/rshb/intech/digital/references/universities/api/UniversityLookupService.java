package ru.rshb.intech.digital.references.universities.api;

import jakarta.annotation.Nonnull;
import java.util.List;

public interface UniversityLookupService {

  @Nonnull
  List<University> findUniversityByTitle(@Nonnull String universityTitle);

  @Nonnull
  List<University> findUniversityByTitle(@Nonnull String universityTitle, int limit);

}
