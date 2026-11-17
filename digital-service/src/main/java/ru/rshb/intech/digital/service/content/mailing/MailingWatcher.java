package ru.rshb.intech.digital.service.content.mailing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.subscriptions.core.service.mailing.MailerService;
import ru.rshb.intech.digital.subsystem.audit.event.AuditLogEvent;
import ru.rshb.intech.digital.subsystem.audit.event.LogLevel;
import ru.rshb.intech.digital.subsystem.events.AppEventBus;
import ru.rshb.intech.digital.subsystem.events.SubSystem;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailingWatcher {

  private final MailerService mailerService;
  private final AppEventBus appEventBus;


  @Scheduled(cron = "${subscriptions.digest.collection.cron}")
  public void doMailing() {

    log.info("Starting mailing job...");
    appEventBus.log(
        new AuditLogEvent(this, LogLevel.INFO, SubSystem.MAILING)
            .withTitleLocalized("audit.event.mailing.process.start.title")
            .withDescriptionLocalized("audit.event.mailing.process.start.description")
    );

    try {
      final long processed = mailerService.collectAndSend();

      log.info("Mailing job completed. Processed: {}", processed);
      appEventBus.log(
          new AuditLogEvent(this, LogLevel.INFO, SubSystem.MAILING)
              .withTitleLocalized("audit.event.mailing.process.finish.success.title")
              .withDescriptionLocalized("audit.event.mailing.process.finish.success.description")
      );

    } catch (Exception e) {

      log.error("Mailing error", e);
      appEventBus.log(
          new AuditLogEvent(this, LogLevel.ERROR, SubSystem.MAILING)
              .withTitleLocalized("audit.event.mailing.process.finish.error.title")
              .withDescriptionLocalized("audit.event.mailing.process.finish.error.description")
              .withThrowable(e)
      );
    }
  }
}
