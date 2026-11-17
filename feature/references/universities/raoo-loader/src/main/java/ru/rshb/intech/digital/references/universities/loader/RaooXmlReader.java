package ru.rshb.intech.digital.references.universities.loader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import jakarta.annotation.Nonnull;
import java.io.File;

public class RaooXmlReader implements RaooReader {

  @Override
  public long read(@Nonnull File inputFile, @Nonnull RaooWriter writer) {

    XmlMapper mapper = new XmlMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    try {
      final var data = mapper.readValue(inputFile, OpenData.class);
      for (final var item : data.getCertificates()) {
        writer.write(item);
      }
      return data.getCertificates().size();
    } catch (Exception e) {
      throw new RuntimeException("RAOO reading error", e);
    }
  }
}
