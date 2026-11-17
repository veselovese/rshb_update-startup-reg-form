package ru.rshb.intech.digital.references.universities.loader;

import jakarta.annotation.Nonnull;
import java.io.File;

public interface RaooConverter {

  long convert(@Nonnull File inputFile, @Nonnull File outputFile) throws Exception;
}
