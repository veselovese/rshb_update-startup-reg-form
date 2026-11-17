package ru.rshb.intech.digital.notifications.core.template.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.notifications.api.exception.NotificationException;
import ru.rshb.intech.digital.notifications.core.template.repository.TemplateRepository;
import ru.rshb.intech.digital.notifications.entity.NotificationTemplate;

import jakarta.annotation.Nonnull;

@Service
@RequiredArgsConstructor
public class NotificationTemplateServiceImpl implements NotificationTemplateService {

  private final TemplateRepository templateRepository;

  @Nonnull
  @Override
  public NotificationTemplate getTemplateByKey(@Nonnull String templateKey) {
    return templateRepository.findByKey(templateKey)
        .orElseThrow(() -> new NotificationException("Notification template [" + templateKey + "] not found"));
  }
}
