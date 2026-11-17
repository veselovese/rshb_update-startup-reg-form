package ru.rshb.intech.digital.references.cities.okato.loader.writer;

import ru.rshb.intech.digital.references.cities.okato.loader.OkatoItem;

import jakarta.annotation.Nonnull;

public interface OkatoWriter {

  void write(@Nonnull OkatoItem item);
}
