package ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto.AgrotechOlympiadRegLookupsDto;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.dto.LookupItemDto;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.AgrotechOlympiadDirection;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity.University;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.repository.AgrotechOlympiadDirectionRepository;
import ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.repository.AgrotechOlympiadUniversityRepository;
import ru.rshb.intech.digital.references.universities.dto.UniversityDto;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgrotechOlympiadRegistrationLookupServiceImpl implements AgrotechOlympiadRegistrationLookupService {

  private final AgrotechOlympiadDirectionRepository directionRepository;
  private final AgrotechOlympiadUniversityRepository universityRepository;

  @Override
  @Nonnull
  public Optional<String> findDirection(@Nonnull String directionId) {
    return directionRepository.findByExternalIdIgnoreCase(directionId)
        .map(AgrotechOlympiadDirection::getTitle);
  }

  @Override
  @Nonnull
  public AgrotechOlympiadRegLookupsDto getFixedLookups() {
    return new AgrotechOlympiadRegLookupsDto(
        mapDirections(directionRepository.findAllByDisabledIsFalseOrderById()),
        mapTopUniversities(universityRepository.findAllTopUniversities())
    );
  }

  @Nonnull
  private List<LookupItemDto> mapDirections(@Nonnull List<AgrotechOlympiadDirection> directions) {
    return directions.stream()
        .map(direction -> new LookupItemDto(direction.getExternalId(), direction.getTitle()))
        .toList();
  }

  @Nonnull
  private List<LookupItemDto> mapTopUniversities(@Nonnull List<University> universities) {
    return universities.stream()
        .map(university -> new LookupItemDto(university.getExternalId(), university.getShortTitle()))
        .toList();
  }

  @Override
  @Nonnull
  public List<UniversityDto> findUniversitiesByTitle(@Nonnull String title) {
    return universityRepository.findAllByTitleIgnoreCase(title, Pageable.ofSize(5))
        .stream()
        .map(uni -> new UniversityDto(uni.getExternalId(), uni.getFullTitle(), uni.getShortTitle()))
        .toList();
  }

  @Override
  @Nonnull
  public Optional<University> findUniversityByExternalId(@Nonnull String externalId) {
    return universityRepository.findByExternalIdIgnoreCase(externalId);
  }

  @Override
  @Nonnull
  public String getUniversityShortTitleOrEmpty(@Nonnull String universityId) {
    return universityRepository.findByExternalIdIgnoreCase(universityId)
        .map(University::getShortTitle)
        .orElse(StringUtils.EMPTY);
  }
}
