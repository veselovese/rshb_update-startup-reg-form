package ru.rshb.intech.digital.feature.statistics.agrobit.university.entity;

import lombok.RequiredArgsConstructor;
import ru.rshb.intech.digital.messages.I18n;

import jakarta.annotation.Nonnull;

@RequiredArgsConstructor
public enum EduType {

  BUDGET("agrobit.statistics.university.edu.type.budget"),

  COMMERCIAL("agrobit.statistics.university.edu.type.commercial");

  private final String titleKey;

  @Nonnull
  public String getTitle() {
    return I18n.of(titleKey);
  }
}
