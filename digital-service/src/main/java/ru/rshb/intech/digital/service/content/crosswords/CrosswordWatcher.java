package ru.rshb.intech.digital.service.content.crosswords;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.content.provider.api.CrosswordContentService;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.puzzles.core.crossword.service.CrosswordUpdater;
import ru.rshb.intech.digital.subsystem.audit.event.AuditLogEvent;
import ru.rshb.intech.digital.subsystem.audit.event.LogLevel;
import ru.rshb.intech.digital.subsystem.events.AppEventBus;
import ru.rshb.intech.digital.subsystem.events.SubSystem;

@Slf4j
@Component
@RequiredArgsConstructor
public class CrosswordWatcher {

  private final CrosswordContentService crosswordContentService;
  private final CrosswordUpdater crosswordUpdater;
  private final AppEventBus appEventBus;

  @Scheduled(
      fixedRateString = "${content.check-interval}",
      initialDelayString = "${content.check-delay}"
  )
  @Transactional
  public void updateCrosswords() {
    try {
      log.debug("Crosswords update...");
      final var cachedCrosswords = crosswordContentService.findAll();
      log.debug("Cached crosswords: {}", cachedCrosswords.size());

      crosswordUpdater.update(cachedCrosswords);
      log.debug("Crosswords update completed");
    } catch (DataAccessResourceFailureException e) {
      handleDataAccessError(e);
    } catch (Exception e) {
      handleCommonError(e);
    }
  }

  private void handleDataAccessError(@Nonnull DataAccessResourceFailureException e) {
    log.error("Crosswords update error", e);
    appEventBus.log(
        new AuditLogEvent(this, LogLevel.ERROR, SubSystem.GAMES)
            .withTitleLocalized("audit.event.crosswords.sync.error.title")
            .withDescription(I18n.of("audit.event.crosswords.sync.error.description") + "\n" + e.getMessage())
    );
  }

  private void handleCommonError(@Nonnull Exception e) {
    log.error("Crosswords update error", e);
    appEventBus.log(
        new AuditLogEvent(this, LogLevel.ERROR, SubSystem.GAMES)
            .withTitleLocalized("audit.event.crosswords.sync.error.title")
            .withDescriptionLocalized("audit.event.crosswords.sync.error.description")
            .withThrowable(e)
    );
  }
}
