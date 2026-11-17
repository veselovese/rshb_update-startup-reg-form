package ru.rshb.intech.digital.feature.contests.chef.service.contestant.form.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.feature.contests.chef.dto.ChefContestantFormData;
import ru.rshb.intech.digital.feature.contests.chef.entity.ChefContestant;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ContestantNameTest {

  @InjectMocks
  private ContestantName contestantNameAttribute;

  private ChefContestantFormData dto;
  private ChefContestant entity;
  private FormContext<ChefContestantFormData, ChefContestant> formContext;

  @BeforeEach
  void setUp() {
    dto = new ChefContestantFormData();
    entity = new ChefContestant();
    formContext = new FormContext<>("chefContestantForm", dto, entity);
  }

  @Test
  void supplyInputValue() {

    final var testData = "Иванов Иван Иванович";

    dto.setContestantName(testData);

    final String suppliedValue = contestantNameAttribute.supplyInputValue(dto);

    assertThat(suppliedValue)
        .isEqualTo(testData);
  }

  @Test
  void consumeOutputValue() {

    final var testData = "Иванов Иван Иванович";

    contestantNameAttribute.consumeOutputValue(testData, entity);

    assertThat(
        entity.getName()
    ).isEqualTo(testData);
  }

  @Test
  void processWithPreCheck() {
    assertThat(
        contestantNameAttribute.processWithPreCheck()
    ).isTrue();
  }
}