package ru.rshb.intech.digital.puzzles.core.farm;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.puzzles.dto.farm.CommonUsageStatsDto;
import ru.rshb.intech.digital.subsystem.audit.event.AuditLogEvent;
import ru.rshb.intech.digital.subsystem.audit.event.LogLevel;
import ru.rshb.intech.digital.subsystem.events.AppEventBus;
import ru.rshb.intech.digital.subsystem.events.SubSystem;

@Service
@RequiredArgsConstructor
public class DigitalFarmStatsServiceImpl implements DigitalFarmStatsService {

  private static final CommonUsageStatsDto DEFAULT_COMMON_USAGE_STATS = new CommonUsageStatsDto(5D, 100);

  private final AppEventBus appEventBus;
  private final DigitalFarmGameClient gameClient;

  @Nonnull
  @Override
  public CommonUsageStatsDto getCommonUsageStats() {
    try {
      final var stats = gameClient.getCommonUsageStats();
      return new CommonUsageStatsDto(stats.avgMark(), stats.activeUsersNum());
    } catch (Exception e) {
      appEventBus.log(
          new AuditLogEvent(this, LogLevel.ERROR, SubSystem.GAMES)
              .withTitleLocalized("audit.event.games.digital-farm.integration.error.title")
              .withDescriptionLocalized("audit.event.games.digital-farm.integration.error.description")
              .withThrowable(e)
      );
      return DEFAULT_COMMON_USAGE_STATS;
    }
  }
}
