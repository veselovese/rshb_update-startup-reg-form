package ru.rshb.intech.digital.notifications.mail.template;

import jakarta.annotation.Nonnull;

public interface HtmlTemplate<D> {

  @Nonnull
  String toHtml(@Nonnull D data);
}
