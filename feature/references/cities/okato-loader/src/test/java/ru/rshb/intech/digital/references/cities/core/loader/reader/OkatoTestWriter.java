package ru.rshb.intech.digital.references.cities.core.loader.reader;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.references.cities.okato.loader.OkatoItem;
import ru.rshb.intech.digital.references.cities.okato.loader.writer.OkatoWriter;


public class OkatoTestWriter implements OkatoWriter {

  @Override
  public void write(@Nonnull OkatoItem item) {
    final var sb = new StringBuilder();
    sb.append(item.getCode())
        .append("|").append(item.getParent().getTitle())
        .append("|").append(item.getTitle())
        .append("|").append(item.getAdministrativeCenter());
    System.out.println(sb);
  }
}
