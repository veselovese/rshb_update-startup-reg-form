package ru.rshb.intech.digital.subscriptions.core.service.activation;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.notifications.core.template.service.NotificationTemplateService;
import ru.rshb.intech.digital.notifications.mail.template.AbstractHtmlTemplate;
import ru.rshb.intech.digital.notifications.mail.template.HtmlTemplateConverter;

import jakarta.annotation.Nonnull;
import java.util.Map;

@Component
public class ActivationEmailTemplate extends AbstractHtmlTemplate<ActivationInfo> {

  private static final String TEMPLATE_KEY = "news.subscription.activation.confirmation";

  private final NotificationTemplateService templateService;


  public ActivationEmailTemplate(@Nonnull HtmlTemplateConverter converter,
                                 @Nonnull NotificationTemplateService templateService) {

    super(TEMPLATE_KEY, converter);
    this.templateService = templateService;
  }

  @Nonnull
  @Override
  public String toHtml(@Nonnull ActivationInfo data) {

    final var template = templateService.getTemplateByKey(TEMPLATE_KEY);
    final var templateValues = Map.<String, Object>of("activationUrl", data.getActivationUrl());

    return converter()
        .toHtmlFromString(
            template.getContent(),
            templateValues
        );
  }
}
