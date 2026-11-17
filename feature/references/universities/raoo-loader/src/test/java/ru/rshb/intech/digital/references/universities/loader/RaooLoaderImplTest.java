package ru.rshb.intech.digital.references.universities.loader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RaooLoaderImplTest {

  private RaooConverter converter;


  @BeforeEach
  void setUp() {
    this.converter = new RaooSqlConverter();
  }

  @Disabled
  @Test
  void load() throws Exception {

    final var url = "https://islod.obrnadzor.gov.ru/accredreestr/opendata";
    final var inputFile = "raoo.zip";
    final var outputFile = new File("raoo.sql");

    // download
    // final var localFile = WebUtils.download(url, inputFile);

    // unpack
    // final var unpackedFile = ZipUtils.unpack(localFile);

    final var unpackedFile = new File("data-20230124-structure-20160713.xml");
    final var readCount = converter.convert(unpackedFile, outputFile);
    System.out.println("READ: " + readCount);

    assertNotEquals(0, readCount);
  }
}