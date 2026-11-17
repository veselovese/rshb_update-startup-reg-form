package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto.AgrotechOlympiadRegLookupsDto;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.University;
import ru.rshb.intech.digital.references.universities.dto.UniversityDto;

import java.util.List;
import java.util.Optional;

public interface AgrotechOlympiadRegistrationLookupService {

  @Nonnull
  Optional<String> findDirection(@Nonnull String directionId);

  @Nonnull
  AgrotechOlympiadRegLookupsDto getFixedLookups();

  @Nonnull
  List<UniversityDto> findUniversitiesByTitle(@Nonnull String title);

  @Nonnull
  Optional<University> findUniversityByExternalId(@Nonnull String externalId);

  @Nonnull
  String getUniversityShortTitleOrEmpty(@Nonnull String universityId);

}
