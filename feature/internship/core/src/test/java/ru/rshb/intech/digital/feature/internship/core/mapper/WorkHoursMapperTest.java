package ru.rshb.intech.digital.feature.internship.core.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.feature.internship.core.dto.WorkHoursDto;
import ru.rshb.intech.digital.feature.internship.core.entity.WorkHours;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class WorkHoursMapperTest {

  @InjectMocks
  private WorkHoursMapperImpl workHoursMapper;

  @Test
  void givenNotNull_fromDtos_thenReturnResult() {

    final List<WorkHours> result = workHoursMapper.fromDtos(
        List.of(new WorkHoursDto("123", "20 часов"))
    );

    assertThat(result)
        .isNotNull();

    assertThat(result.size())
        .isEqualTo(1);
  }

  @Test
  void givenNull_fromDtos_thenReturnNull() {

    final List<WorkHours> result = workHoursMapper.fromDtos(null);

    assertThat(result)
        .isNull();
  }

  @Test
  void givenNotNull_fromDto_thenReturnResult() {

    final var dto = new WorkHoursDto("123", "20 часов");

    final WorkHours result = workHoursMapper.fromDto(dto);

    assertThat(result)
        .isNotNull();
    assertThat(result.getId())
        .isNull();
    assertThat(result.getExternalId())
        .isEqualTo("123");
    assertThat(result.getTitle())
        .isEqualTo("20 часов");
    assertThat(result.getEnabled())
        .isTrue();
  }

  @Test
  void givenNull_fromDto_thenReturnNull() {

    final WorkHours result = workHoursMapper.fromDto(null);

    assertThat(result)
        .isNull();
  }

  @Test
  void givenNotNull_fromEntity_thenReturnResult() {

    final var entity = new WorkHours();
    entity.setId(1L);
    entity.setExternalId("123");
    entity.setTitle("20 часов");
    entity.setEnabled(true);

    final WorkHoursDto result = workHoursMapper.fromEntity(entity);

    assertThat(result)
        .isNotNull();
    assertThat(result.getId())
        .isEqualTo("123");
    assertThat(result.getTitle())
        .isEqualTo("20 часов");
  }

  @Test
  void givenNull_fromEntity_thenReturnNull() {

    final WorkHoursDto result = workHoursMapper.fromEntity(null);

    assertThat(result)
        .isNull();
  }

  @Test
  void givenNotNull_fromEntities_thenReturnResult() {

    final var entity = new WorkHours();
    entity.setId(1L);
    entity.setExternalId("123");
    entity.setTitle("20 часов");
    entity.setEnabled(true);

    final List<WorkHoursDto> result = workHoursMapper.fromEntities(List.of(entity));

    assertThat(result)
        .isNotNull();

    assertThat(result.size())
        .isEqualTo(1);
  }

  @Test
  void givenNull_fromEntities_thenReturnNull() {

    final List<WorkHoursDto> result = workHoursMapper.fromEntities(null);

    assertThat(result)
        .isNull();
  }
}