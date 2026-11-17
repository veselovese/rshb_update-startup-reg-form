package ru.rshb.intech.digital.feature.internship.core.service.fields;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFieldDto;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipField;
import ru.rshb.intech.digital.feature.internship.core.mapper.InternshipFieldMapperImpl;
import ru.rshb.intech.digital.feature.internship.core.repository.fields.InternshipFieldRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class InternshipFieldServiceTest {

  @Mock
  private InternshipFieldRepository fieldRepository;

  private InternshipFieldService fieldService;


  @BeforeEach
  void setUp() {
    fieldService = new InternshipFieldServiceImpl(
        fieldRepository,
        new InternshipFieldMapperImpl()
    );
  }

  @Test
  void findAvailableInternshipFields() {

    given(
        fieldRepository.findAllEnabled()
    ).willReturn(
        List.of(new InternshipField())
    );

    final List<InternshipFieldDto> result = fieldService.findAvailableInternshipFields();

    assertThat(result)
        .isNotNull()
        .size()
        .isEqualTo(1);
  }

  @Test
  void findAvailableFieldTitles() {

    final var field = new InternshipField();
    field.setId(1L);
    field.setKey("development");
    field.setTitle("Разработка");
    field.setEnabled(true);
    field.setDeleted(false);

    given(
        fieldRepository.findAllEnabled()
    ).willReturn(
        List.of(field)
    );

    final List<String> result = fieldService.findAvailableFieldTitles();

    assertThat(result)
        .isNotNull()
        .size()
        .isEqualTo(1);
  }

  @Test
  void findAvailableFieldTitlesIfPresent() {

    given(
        fieldRepository.findEnabledWithTitlesIn(anySet())
    ).willReturn(
        List.of("Разработка")
    );

    final var testFieldTitles = List.of("", "Разработка");

    final List<String> result = fieldService.findAvailableFieldTitlesIfPresent(testFieldTitles);

    assertThat(result)
        .isNotNull()
        .size()
        .isEqualTo(1);
  }
}