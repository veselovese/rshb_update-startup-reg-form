package ru.rshb.intech.digital.feature.internship.core.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipFieldDto;
import ru.rshb.intech.digital.feature.internship.core.entity.InternshipField;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class InternshipFieldMapperImplTest {

  @InjectMocks
  private InternshipFieldMapperImpl internshipFieldMapper;

  @BeforeEach
  void setUp() {
  }

  @Test
  void givenNotNull_fromDtos_thenReturnResult() {

    final var dto = new InternshipFieldDto(
        1L,
        "development",
        "Разработка",
        true,
        true
    );

    final List<InternshipField> result = internshipFieldMapper.fromDtos(List.of(dto));

    assertThat(result)
        .isNotNull();

    assertThat(result.size())
        .isEqualTo(1);
  }

  @Test
  void givenNull_fromDtos_thenReturnNull() {

    final List<InternshipField> result = internshipFieldMapper.fromDtos(null);

    assertThat(result)
        .isNull();
  }

  @Test
  void givenNotNull_fromDto_thenReturnResult() {

    final var dto = new InternshipFieldDto(
        1L,
        "development",
        "Разработка",
        true,
        true
    );

    final InternshipField result = internshipFieldMapper.fromDto(dto);

    assertThat(result)
        .isNotNull();
    assertThat(result.getKey())
        .isEqualTo("development");
    assertThat(result.getTitle())
        .isEqualTo("Разработка");
    assertThat(result.getId())
        .isNull();
    assertThat(result.getEnabled())
        .isNull();
    assertThat(result.getDeleted())
        .isNull();
  }

  @Test
  void givenNull_fromDto_thenReturnNull() {

    final InternshipField result = internshipFieldMapper.fromDto(null);

    assertThat(result)
        .isNull();
  }

  @Test
  void givenNotNull_fromEntity_thenReturnResult() {

    final var entity = new InternshipField();
    entity.setId(1L);
    entity.setKey("development");
    entity.setTitle("Разработка");
    entity.setEnabled(true);
    entity.setDeleted(false);

    final InternshipFieldDto result = internshipFieldMapper.fromEntity(entity);

    assertThat(result)
        .isNotNull();
    assertThat(result.getId())
        .isNull();
    assertThat(result.getKey())
        .isEqualTo("development");
    assertThat(result.getTitle())
        .isEqualTo("Разработка");
    assertThat(result.getEnabled())
        .isNull();
    assertThat(result.getDeleted())
        .isNull();
  }

  @Test
  void givenNull_fromEntity_thenReturnNull() {

    final InternshipFieldDto result = internshipFieldMapper.fromEntity(null);

    assertThat(result)
        .isNull();
  }

  @Test
  void givenNotNull_fromEntities_thenReturnResult() {

    final var entity = new InternshipField();
    entity.setId(1L);
    entity.setKey("development");
    entity.setTitle("Разработка");
    entity.setEnabled(true);
    entity.setDeleted(false);

    final List<InternshipFieldDto> result = internshipFieldMapper.fromEntities(List.of(entity));

    assertThat(result)
        .isNotNull();

    assertThat(result.size())
        .isEqualTo(1);
  }

  @Test
  void givenNull_fromEntities_thenReturnNull() {

    final List<InternshipFieldDto> result = internshipFieldMapper.fromEntities(null);

    assertThat(result)
        .isNull();
  }
}