package ru.rshb.intech.digital.feature.contests.patent.service.registration.form.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.feature.contests.patent.dto.PatentContestRegistrationRequestDto;
import ru.rshb.intech.digital.feature.contests.patent.entity.PatentContestRegistration;
import ru.rshb.intech.digital.messages.I18n;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(MockitoExtension.class)
class StaffSizeTest {

  @InjectMocks
  private StaffSize staffSizeAttribute;

  private PatentContestRegistrationRequestDto dto;
  private PatentContestRegistration entity;
  private FormContext<PatentContestRegistrationRequestDto, PatentContestRegistration> context;

  @BeforeEach
  void setUp() {
    dto = new PatentContestRegistrationRequestDto();
    entity = new PatentContestRegistration();
    context = new FormContext<>("patentContestRegistrationForm", dto, entity);
  }

  @Test
  void givenNull_process_thenSuccess() {

    final Integer testData = null;

    dto.setStaffSize(testData);

    staffSizeAttribute.process(context);

    assertThat(
        entity.getStaffSize()
    ).isNull();
  }

  @Test
  void givenNonPositive_process_thenException() {

    final Integer testData = -1;

    dto.setStaffSize(testData);

    final var exception = assertThrowsExactly(
        AttributeBadFormatException.class,
        () -> staffSizeAttribute.process(context)
    );

    assertThat(
        exception.getMessage()
    ).isEqualTo(
        I18n.of("validation.error.attribute.data.format", staffSizeAttribute.properties().getName())
    );
  }

  @Test
  void supplyInputValue() {

    final var testData = 11;
    dto.setStaffSize(11);

    final Integer suppliedValue = staffSizeAttribute.supplyInputValue(dto);

    assertThat(suppliedValue)
        .isEqualTo(testData);
  }

  @Test
  void consumeOutputValue() {

    final var testData = 11;

    staffSizeAttribute.consumeOutputValue(testData, entity);

    assertThat(
        entity.getStaffSize()
    ).isEqualTo(testData);
  }
}