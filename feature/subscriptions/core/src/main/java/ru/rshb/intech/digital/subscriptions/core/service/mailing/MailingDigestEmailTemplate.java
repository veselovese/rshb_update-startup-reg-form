package ru.rshb.intech.digital.subscriptions.core.service.mailing;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.utils.Values;
import ru.rshb.intech.digital.notifications.core.template.service.NotificationTemplateService;
import ru.rshb.intech.digital.notifications.mail.template.AbstractHtmlTemplate;
import ru.rshb.intech.digital.notifications.mail.template.HtmlTemplateConverter;

import jakarta.annotation.Nonnull;
import java.util.ArrayList;

@Component
public class MailingDigestEmailTemplate extends AbstractHtmlTemplate<MailingDigest> {

  private static final String TEMPLATE_KEY = "news.subscription.mailing.digest";

  private final NotificationTemplateService templateService;


  public MailingDigestEmailTemplate(@Nonnull HtmlTemplateConverter converter,
                                    @Nonnull NotificationTemplateService templateService) {

    super(TEMPLATE_KEY, converter);
    this.templateService = templateService;
  }

  @Nonnull
  @Override
  public String toHtml(@Nonnull MailingDigest data) {

    final var template = templateService.getTemplateByKey(TEMPLATE_KEY);
    final var templateValues = new Values()
        .put("newsItems", new ArrayList<>(data.getNews())) // Обертка нужна для работы thymeleaf.
        .put("eventsItems", new ArrayList<>(data.getEvents()))
        .put("podcastsItems", new ArrayList<>(data.getPodcasts()))
        .put("unsubscribeLink", data.getDeactivationUrl())
        .asMap();

    return converter()
        .toHtmlFromString(
            template.getContent(),
            templateValues
        );
  }
}
