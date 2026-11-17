package ru.rshb.intech.digital.feature.startups.core.service.importing;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.rshb.intech.digital.feature.startups.dto.StartupArchive;
import ru.rshb.intech.digital.feature.startups.dto.StartupFileDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Zip-архив с файлами карточек стартапов/компаний в формате JSON.
 */
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ZipStartupsArchive implements StartupArchiver {

  private final List<StartupFileDto> fileBuf = new ArrayList<>();
  private final File tmpDir;

  /**
   * Создает новый инстанс архиватора.
   * @param workDir Рабочая папка.
   * @return Инстанс архиватора.
   */
  @Nonnull
  public static StartupArchiver create(@Nonnull File workDir) {
    return new ZipStartupsArchive(workDir);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void add(@Nonnull StartupFileDto startupFile) {
    fileBuf.add(startupFile);
  }

  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public StartupArchive compress(@Nonnull String outputFileName) {

    final var outputFile = new File(tmpDir, outputFileName);

    try (final var outputBuf = new FileOutputStream(outputFile);
         final var zipOutput = new ZipOutputStream(outputBuf)) {

      for (final var file : fileBuf) {
        addToArchive(file, zipOutput);
      }

      return new StartupArchive(outputFileName,  outputFile);
    } catch (Exception e) {
      throw new RuntimeException("Archive creation error", e);
    }
  }

  private void addToArchive(@Nonnull StartupFileDto fileDto,
                            @Nonnull ZipOutputStream zos) {

    try (final var fis = new FileInputStream(fileDto.cachedFile())) {

      final var zipEntry = new ZipEntry(fileDto.fileName());
      zos.putNextEntry(zipEntry);
      zos.write(fis.readAllBytes());
      zos.closeEntry();

    } catch (IOException e) {
      log.error("Add file to archive error", e);
    }
  }
}
