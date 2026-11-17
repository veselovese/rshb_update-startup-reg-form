package ru.rshb.intech.digital.references.cities.okato.loader.reader;

import jakarta.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

public interface OkatoReader {

  long read(@Nonnull File localFile) throws IOException;

}
