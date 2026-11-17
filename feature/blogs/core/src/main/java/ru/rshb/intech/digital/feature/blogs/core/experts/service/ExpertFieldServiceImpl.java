package ru.rshb.intech.digital.feature.blogs.core.experts.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.blogs.core.experts.dto.ExpertFieldDto;
import ru.rshb.intech.digital.feature.blogs.core.experts.entity.ExpertField;
import ru.rshb.intech.digital.feature.blogs.core.experts.repository.ExpertFieldRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ExpertFieldServiceImpl implements ExpertFieldService {

  private final ExpertFieldRepository fieldRepository;

  @Override
  public boolean isAllFieldsExist(@Nonnull Set<String> titles) {
    return fieldRepository.existsAllByTitleIsIn(titles);
  }

  @Override
  public boolean isFieldExist(@Nonnull String title) {
    return fieldRepository.existsByTitleIgnoreCase(title);
  }

  @Nonnull
  @Override
  public List<ExpertFieldDto> getExpertFields() {
    return fieldRepository.findAllByEnabledIsTrueOrderByTitle()
        .stream()
        .map(entity -> mapField(entity))
        .toList();
  }

  @Nonnull
  private ExpertFieldDto mapField(@Nonnull ExpertField entity) {
    final var dto = new ExpertFieldDto();
    dto.setId(entity.getExternalId());
    dto.setValue(entity.getTitle());
    return dto;
  }
}
