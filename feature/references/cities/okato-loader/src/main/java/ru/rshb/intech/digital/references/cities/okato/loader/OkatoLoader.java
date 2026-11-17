package ru.rshb.intech.digital.references.cities.okato.loader;

import jakarta.annotation.Nonnull;

public interface OkatoLoader {

  long load(@Nonnull LoaderOptions options);
}
