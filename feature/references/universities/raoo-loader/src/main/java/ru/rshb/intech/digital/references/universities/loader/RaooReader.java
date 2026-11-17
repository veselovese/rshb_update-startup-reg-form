package ru.rshb.intech.digital.references.universities.loader;

import jakarta.annotation.Nonnull;
import java.io.File;

public interface RaooReader {

  long read(@Nonnull File inputFile, @Nonnull RaooWriter writer);
}
