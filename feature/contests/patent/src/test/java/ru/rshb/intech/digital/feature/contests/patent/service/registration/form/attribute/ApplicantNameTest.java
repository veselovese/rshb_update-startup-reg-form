package ru.rshb.intech.digital.feature.contests.patent.service.registration.form.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.feature.contests.patent.dto.PatentContestRegistrationRequestDto;
import ru.rshb.intech.digital.feature.contests.patent.entity.PatentContestRegistration;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ApplicantNameTest {

  @InjectMocks
  private ApplicantName applicantNameAttribute;

  private PatentContestRegistrationRequestDto dto;
  private PatentContestRegistration entity;
  private FormContext<PatentContestRegistrationRequestDto, PatentContestRegistration> formContext;

  @BeforeEach
  void setUp() {
    dto = new PatentContestRegistrationRequestDto();
    entity = new PatentContestRegistration();
    formContext = new FormContext<>("patentContestRegForm", dto, entity);
  }

  @Test
  void supplyInputValue() {

    final var testData = "Иванов Иван Иванович";

    dto.setApplicantName(testData);

    final String result = applicantNameAttribute.supplyInputValue(dto);

    assertThat(result)
        .isEqualTo(testData);
  }

  @Test
  void consumeOutputValue() {

    final var testData = "Иванов Иван Иванович";

    applicantNameAttribute.consumeOutputValue(testData, entity);

    assertThat(
        entity.getApplicantName()
    ).isEqualTo(testData);
  }
}