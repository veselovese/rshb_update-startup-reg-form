package ru.rshb.intech.digital.feature.internship.core.service.hours;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.feature.internship.core.dto.WorkHoursDto;
import ru.rshb.intech.digital.feature.internship.core.entity.WorkHours;
import ru.rshb.intech.digital.feature.internship.core.mapper.WorkHoursMapperImpl;
import ru.rshb.intech.digital.feature.internship.core.repository.hours.WorkHoursRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class WorkHoursServiceTest {

  @Mock
  private WorkHoursRepository workHoursRepository;

  private WorkHoursService workHoursService;

  @BeforeEach
  void setUp() {
    workHoursService = new WorkHoursServiceImpl(
        workHoursRepository,
        new WorkHoursMapperImpl()
    );
  }

  @Test
  void findEnabledWorkHours() {

    given(
        workHoursRepository.findAllByEnabledIsTrue()
    ).willReturn(
        List.of(new WorkHours(1L, "123", "20 часов"))
    );

    final List<WorkHoursDto> result = workHoursService.findEnabledWorkHours();

    assertThat(result)
        .isNotNull()
        .size()
        .isEqualTo(1);
  }

  @Test
  void isWorkHoursExist() {

    given(
        workHoursRepository.existsByTitleIgnoreCase("20 часов")
    ).willReturn(true);

    final boolean result = workHoursService.isWorkHoursExist("20 часов");

    assertThat(result)
        .isTrue();
  }

  @Test
  void givenNotExist_isWorkHoursNotExist_thenReturnTrue() {

    given(
        workHoursRepository.existsByTitleIgnoreCase(anyString())
    ).willReturn(false);

    final boolean result = workHoursService.isWorkHoursNotExist("20 часов");

    assertThat(result)
        .isTrue();
  }

  @Test
  void givenExist_isWorkHoursNotExist_thenReturnFalse() {

    given(
        workHoursRepository.existsByTitleIgnoreCase(anyString())
    ).willReturn(true);

    final boolean result = workHoursService.isWorkHoursNotExist("20 часов");

    assertThat(result)
        .isFalse();
  }
}