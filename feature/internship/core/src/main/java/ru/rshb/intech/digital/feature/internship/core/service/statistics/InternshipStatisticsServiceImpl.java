package ru.rshb.intech.digital.feature.internship.core.service.statistics;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.internship.core.dto.statistics.InternshipStatisticsDto;
import ru.rshb.intech.digital.feature.internship.core.entity.RegistrationMonthlyStats;
import ru.rshb.intech.digital.feature.internship.core.mapper.statistics.RegistrationMonthlyStatsMapper;
import ru.rshb.intech.digital.feature.internship.core.repository.statistics.RegistrationMonthlyStatsRepository;
import ru.rshb.intech.digital.feature.internship.core.service.fields.InternshipFieldService;
import ru.rshb.intech.digital.feature.internship.core.service.statistics.registration.RegistrationMonthlyRelativeStatisticsCollector;
import ru.rshb.intech.digital.messages.I18n;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class InternshipStatisticsServiceImpl implements InternshipStatisticsService {

  private final RegistrationMonthlyRelativeStatisticsCollector monthlyRelativeStatisticsCollector;
  private final RegistrationMonthlyStatsRepository monthlyStatsRepository;
  private final InternshipFieldService fieldService;
  private final RegistrationMonthlyStatsMapper monthlyStatsMapper;


  @Nonnull
  @Override
  @Transactional
  public InternshipStatisticsDto getMonthlyRelativeStatistics(@Nonnull InternshipStatisticsFilter filter) {

    final var fields = getValidatedFields(filter.fields());
    fields.add(allFieldsTitle());

    final var stats = monthlyStatsRepository.findAllByFieldIn(fields);

    return monthlyStatsMapper.map(stats);
  }

  @Nonnull
  private String allFieldsTitle() {
    return I18n.of("internship.registration.fields.all");
  }

  @Nonnull
  private Set<String> getValidatedFields(@Nonnull List<String> nonValidatedFields) {
    final var list = nonValidatedFields.isEmpty()
        ? fieldService.findAvailableFieldTitles()
        : fieldService.findAvailableFieldTitlesIfPresent(nonValidatedFields);
    return new HashSet<>(list);
  }

  @Nonnull
  @Override
  public List<RegistrationMonthlyStats> refreshMonthlyStats() {

    log.debug("Registration monthly stats refreshing procedure started...");
    final var stats = monthlyRelativeStatisticsCollector.collectForCaching();
    log.debug("Registration monthly stats refreshing proceudre: collected {} items", stats.size());

    monthlyStatsRepository.deleteAll();
    log.debug("Registration monthly stats table wiped out");

    final var storedStats = monthlyStatsRepository.saveAllAndFlush(stats);
    log.debug("Registration monthly stats successfully saved: {}", storedStats.size());

    return storedStats;
  }
}
