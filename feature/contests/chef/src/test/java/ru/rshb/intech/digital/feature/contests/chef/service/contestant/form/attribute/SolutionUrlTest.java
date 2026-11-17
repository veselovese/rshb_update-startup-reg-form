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
class SolutionUrlTest {

  @InjectMocks
  private SolutionUrl solutionUrlAttribute;

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
  void processOrder() {
    assertThat(
        solutionUrlAttribute.processOrder()
    ).isEqualTo(1);
  }

  @Test
  void supplyInputValue() {

    final var testData = "https://google.com";

    dto.setUrl(testData);

    final String suppliedValue = solutionUrlAttribute.supplyInputValue(dto);

    assertThat(suppliedValue)
        .isEqualTo(testData);
  }

  @Test
  void consumeOutputValue() {

    final var testData = "https://google.com";

    solutionUrlAttribute.consumeOutputValue(testData, entity);

    assertThat(
        entity.getSolutionUrl()
    ).isEqualTo(testData);
  }
}