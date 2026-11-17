package ru.rshb.intech.digital.notifications.mail.template;

import lombok.RequiredArgsConstructor;

import jakarta.annotation.Nonnull;

@RequiredArgsConstructor
public abstract class AbstractHtmlTemplate<D> implements HtmlTemplate<D> {

  private final String template;
  private final HtmlTemplateConverter converter;


  @Nonnull
  protected String template() {
    return template;
  }

  @Nonnull
  protected HtmlTemplateConverter converter() {
    return converter;
  }
}
