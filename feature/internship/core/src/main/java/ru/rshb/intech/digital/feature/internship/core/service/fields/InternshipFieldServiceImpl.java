package ru.rshb.intech.digital.feature.internship.core.service.fields;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFieldDto;
import ru.rshb.intech.digital.feature.internship.core.mapper.InternshipFieldMapper;
import ru.rshb.intech.digital.feature.internship.core.repository.fields.InternshipFieldRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
@RequiredArgsConstructor
public class InternshipFieldServiceImpl implements InternshipFieldService {

  private final InternshipFieldRepository fieldRepository;
  private final InternshipFieldMapper fieldMapper;


  @Nonnull
  @Override
  public List<InternshipFieldDto> findAvailableInternshipFields() {
    final var enabledFields = fieldRepository.findAllEnabled();
    return fieldMapper.fromEntities(enabledFields);
  }

  @Nonnull
  @Override
  public List<String> findAvailableFieldTitles() {
    return fieldRepository.findAllEnabled()
        .stream()
        .map(field -> field.getTitle())
        .toList();
  }

  @Nonnull
  @Override
  public List<String> findAvailableFieldTitlesIfPresent(@Nonnull List<String> fieldsForCheck) {

    final var preparedFieldsForCheck = fieldsForCheck.stream()
        .filter(field -> isNotBlank(field))
        .map(field -> capitalize(field.toLowerCase()))
        .collect(Collectors.toSet());

    return fieldRepository.findEnabledWithTitlesIn(preparedFieldsForCheck);
  }
}
