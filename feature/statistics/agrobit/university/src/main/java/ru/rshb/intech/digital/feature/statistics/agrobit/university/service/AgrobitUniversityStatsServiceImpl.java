package ru.rshb.intech.digital.feature.statistics.agrobit.university.service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.dto.EduTypeStatsDto;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.dto.FacultyStatsDto;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.dto.UniversityStatsDto;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.dto.YearStatItemDto;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.entity.EduType;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.entity.Faculty;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.repository.AgrobitStatsFacultyRepository;
import ru.rshb.intech.digital.feature.statistics.agrobit.university.repository.AgrobitYearStatsRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgrobitUniversityStatsServiceImpl implements AgrobitUniversityStatsService {

  private final AgrobitStatsFacultyRepository facultyRepository;
  private final AgrobitYearStatsRepository yearStatsRepository;


  @Nonnull
  @Override
  @Transactional
  public List<EduTypeStatsDto> getUniversityStats() {

    final var result = new ArrayList<EduTypeStatsDto>();

    final var minYear = yearStatsRepository.findMinYear();
    final var maxYear = (long) LocalDate.now().getYear();

    for (final var eduType : EduType.values()) {

      final var eduTypeStats = new EduTypeStatsDto(eduType.getTitle());
      result.add(eduTypeStats);

      final var faculties = facultyRepository.findAllByType(eduType);
      final var universities = faculties.stream()
          .map(Faculty::getUniversity)
          .collect(Collectors.toSet());

      for (final var university : universities) {

        final var universityDto = new UniversityStatsDto(university.getName(), university.getShortName());

        eduTypeStats.getUniversities()
            .add(universityDto);

        final var facs = faculties.stream()
            .filter(fac -> Objects.equals(fac.getUniversity(), university))
            .toList();

        for (final var fac : facs) {

          final var facultyDto = new FacultyStatsDto(fac.getName());

          universityDto.getFaculties()
              .add(facultyDto);

          final var stats = fac.getYearStats();
          final var statsMap = statsMap(minYear, maxYear);

          for (final var statItem : stats) {
            final var dto = statsMap.get(statItem.getYear());
            dto.setValue(statItem.getValue());
          }

          facultyDto.getData().addAll(statsMap.values());
        }
      }

    }

    return result;
  }

  @Nonnull
  private Map<Long, YearStatItemDto> statsMap(long minYear, long maxYear) {
    final var statsMap = new HashMap<Long, YearStatItemDto>();
    for (long year = minYear; year <= maxYear; year++) {
      statsMap.put(year, new YearStatItemDto(String.valueOf(year), null));
    }
    return statsMap;
  }
}
