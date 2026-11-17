package ru.rshb.intech.digital.notifications.core.template.service;

import ru.rshb.intech.digital.notifications.entity.NotificationTemplate;

import jakarta.annotation.Nonnull;

public interface NotificationTemplateService {

  @Nonnull
  NotificationTemplate getTemplateByKey(@Nonnull String templateKey);

}
