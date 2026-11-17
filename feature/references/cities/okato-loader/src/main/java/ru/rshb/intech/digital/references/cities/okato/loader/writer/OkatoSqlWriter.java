package ru.rshb.intech.digital.references.cities.okato.loader.writer;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import ru.rshb.intech.digital.references.cities.okato.loader.OkatoItem;

import jakarta.annotation.Nonnull;
import java.io.File;
import java.util.List;

@Slf4j
public class OkatoSqlWriter implements OkatoWriter {

  private static final String ENCODING = "UTF-8";

  private final File outputFile;

  public OkatoSqlWriter(@Nonnull String outputFileName) {
    this.outputFile = new File(outputFileName);
  }

  @Override
  public void write(@Nonnull OkatoItem item) {

    final var sql = String.format(
        "INSERT INTO digital_refs.okato_city(id, region, title) VALUES ('%s', '%s', '%s');",
        item.getCode(), item.getParent().getTitle(), item.getTitle()
    );
    log.info(sql);

    try {
      FileUtils.writeLines(outputFile, ENCODING, List.of(sql), true);
    } catch (Exception e) {
      throw new RuntimeException("Writing error", e);
    }
  }
}
