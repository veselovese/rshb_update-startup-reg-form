package ru.rshb.intech.digital.feature.startups.core.service.importing;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.startups.core.exception.StartupConvertingException;
import ru.rshb.intech.digital.feature.startups.dto.StartupConvertDto;
import ru.rshb.intech.digital.feature.startups.entity.StartupsConvertResult;
import ru.rshb.intech.digital.subsystem.audit.event.AuditLogEvent;
import ru.rshb.intech.digital.subsystem.audit.event.LogLevel;
import ru.rshb.intech.digital.subsystem.events.AppEventBus;
import ru.rshb.intech.digital.subsystem.events.SubSystem;
import ru.rshb.intech.digital.subsystem.events.log.LogEvent;

import java.io.File;

/** {@inheritDoc} */
@Slf4j
@Service
@RequiredArgsConstructor
public class StartupConverterServiceImpl implements StartupConverterService {

  private static final String TMP_DIR_PATH = "/tmp/";
  private static final String STARTUP_UPLOAD_DIR = "/startup-upload";
  private static final int START_ROW_INDEX = 2;
  private static final String OUTPUT_FILE_NAME = "startups.zip";

  private final AppEventBus appEventBus;

  /** {@inheritDoc} */
  @Nonnull
  @Override
  public StartupsConvertResult convert(@Nonnull String sessionId,
                                       @Nonnull StartupConvertDto convertDto) {

    log.info("Startups converting started");

    final var tmpDir = sessionWorkingDir(sessionId);

    try (final var fis = convertDto.startupsFile().getInputStream()) {

      tmpDir.mkdirs();

      final var startupsTable = XlsxStartupsTableFile.open(fis, START_ROW_INDEX);
      final var converter = JsonStartupConverter.create(tmpDir);
      final var archive = ZipStartupsArchive.create(tmpDir);

      for (final var row : startupsTable) {
        final var json = converter.convert(row);
        archive.add(json);
      }

      final var zipArchive = archive.compress(OUTPUT_FILE_NAME);
      final var zipData = FileUtils.readFileToByteArray(zipArchive.cachedFile());

      log.info("Startup converting completed");

      return new StartupsConvertResult(zipArchive.fileName(), zipData);

    } catch (Exception e) {
      appEventBus.log(convertErrorEvent(e));
      throw new StartupConvertingException("Startups converting error", e);
    } finally {
      FileUtils.deleteQuietly(tmpDir);
    }
  }

  @Nonnull
  private File sessionWorkingDir(@Nonnull String sessionId) {
    return new File(TMP_DIR_PATH + sessionId.toLowerCase() + STARTUP_UPLOAD_DIR);
  }

  @Nonnull
  private LogEvent convertErrorEvent(@Nonnull Throwable throwable) {
    return new AuditLogEvent(this, LogLevel.ERROR, SubSystem.COMPANIES)
        .withTitleLocalized("audit.event.internal.companies.converting.error.title")
        .withDescriptionLocalized("audit.event.internal.companies.converting.error.description")
        .withThrowable(throwable);
  }
}
