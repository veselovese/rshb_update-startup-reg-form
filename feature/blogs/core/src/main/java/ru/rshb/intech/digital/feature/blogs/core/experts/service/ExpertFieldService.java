package ru.rshb.intech.digital.feature.blogs.core.experts.service;

import jakarta.annotation.Nonnull;
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertFieldDto;

import java.util.List;
import java.util.Set;

public interface ExpertFieldService {

  boolean isAllFieldsExist(@Nonnull Set<String> titles);

  boolean isFieldExist(@Nonnull String title);

  @Nonnull
  List<ExpertFieldDto> getExpertFields();
}
