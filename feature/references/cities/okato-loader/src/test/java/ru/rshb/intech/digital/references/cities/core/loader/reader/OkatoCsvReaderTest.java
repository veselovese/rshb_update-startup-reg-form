package ru.rshb.intech.digital.references.cities.core.loader.reader;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.rshb.intech.digital.references.cities.okato.loader.reader.OkatoCsvReader;
import ru.rshb.intech.digital.references.cities.okato.loader.reader.OkatoReader;
import ru.rshb.intech.digital.references.cities.okato.loader.writer.OkatoSqlWriter;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Slf4j
class OkatoCsvReaderTest {

  private OkatoReader reader;

  @BeforeEach
  void setUp() {
    this.reader = new OkatoCsvReader(new OkatoSqlWriter("okato.sql"));
  }

  @Test
  void read() throws IOException {

    final var filename = "okato.csv";

    long processed = reader.read(new File(filename));
    assertNotEquals(0L, processed);

    log.info("Processed: {}", processed);
  }
}