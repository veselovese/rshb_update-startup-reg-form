package ru.rshb.intech.digital.references.universities.loader;

import jakarta.annotation.Nonnull;
import java.io.IOException;

public interface RaooWriter {

  void write(@Nonnull OpenData.Certificate certificate) throws IOException;
}
