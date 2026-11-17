package ru.rshb.intech.digital.subsystem.events;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.messages.I18n;

@RequiredArgsConstructor
public enum SubSystem implements EventSource {

  INTERNSHIP("feature.section.internship.title"),
  FEEDBACK("feature.section.feedback.title"),
  VACANCIES("feature.section.vacancies.title"),
  GAMES("feature.section.games.title"),
  COMPANIES("feature.section.companies.title"),

  NOTIFICATIONS("subsystem.notifications.title"),
  WEB("subsystem.web.title"),
  SERVICE("subsystem.service.title"),
  MAILING("subsystem.mailing.title"),
  STATISTICS("subsystem.statistics.title"),
  CENSOR("subsystem.censor.title"),
  CONTENT("subsystem.content.title"),

  VK("subsystem.integration.vk.title")

  ;


  private final String titleKey;


  @Nonnull
  public String getTitle() {
    return I18n.of(titleKey);
  }
}
