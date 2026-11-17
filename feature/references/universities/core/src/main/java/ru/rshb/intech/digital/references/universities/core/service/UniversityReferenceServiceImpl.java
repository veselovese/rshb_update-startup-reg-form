package ru.rshb.intech.digital.references.universities.core.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.references.universities.core.mapper.UniversityMapper;
import ru.rshb.intech.digital.references.universities.core.repository.UniversityRepository;
import ru.rshb.intech.digital.references.universities.dto.UniversityDto;
import ru.rshb.intech.digital.references.universities.entity.University;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
@RequiredArgsConstructor
public class UniversityReferenceServiceImpl implements UniversityReferenceService {

  private static final int RESULTS_OUTPUT_LIMIT = 5;
  private static final int MIN_QUERY_LENGTH = 2;

  private final UniversityRepository universityRepository;
  private final UniversityMapper universityMapper;


  @Nonnull
  @Override
  public List<UniversityDto> findByName(@Nonnull String universityNameQuery) {

    if (StringUtils.trim(universityNameQuery).length() < MIN_QUERY_LENGTH) {
      return Collections.emptyList();
    }

    final var pageable = PageRequest.of(0, RESULTS_OUTPUT_LIMIT);
    final var foundUniversities = universityRepository.findByAnyTitle(universityNameQuery, pageable);

    return universityMapper.map(foundUniversities);
  }

  @Nonnull
  @Override
  public String findUniversityFullNameByIdOrEmpty(@Nullable String universityId) {
    return isNotBlank(universityId)
        ? universityRepository.findByExternalId(universityId)
            .map(University::getFullTitle)
            .orElse(StringUtils.EMPTY)
        : StringUtils.EMPTY;
  }
}
