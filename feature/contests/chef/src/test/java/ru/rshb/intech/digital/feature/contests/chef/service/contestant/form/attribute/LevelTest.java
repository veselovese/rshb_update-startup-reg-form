package ru.rshb.intech.digital.feature.contests.chef.service.contestant.form.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.feature.contests.chef.dto.ChefContestantFormData;
import ru.rshb.intech.digital.feature.contests.chef.entity.ChefContestant;
import ru.rshb.intech.digital.feature.contests.chef.service.ChefContestResultValidator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LevelTest {

  @Mock
  private ChefContestResultValidator resultValidator;

  @InjectMocks
  private Level levelAttribute;

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
        levelAttribute.processOrder()
    ).isEqualTo(2);
  }

  @Test
  void supplyInputValue() {

    final var testData = 1;

    dto.setLevel(testData);

    final Integer suppliedValue = levelAttribute.supplyInputValue(dto);

    assertThat(suppliedValue)
        .isEqualTo(testData);
  }

  @Test
  void consumeOutputValue() {

    final var testData = 1;
    final var expectedResult = 20;

    given(
        resultValidator.calcResultInPercents(testData)
    ).willReturn(expectedResult);

    levelAttribute.consumeOutputValue(testData, entity);

    assertThat(
        entity.getResult()
    ).isEqualTo(
        String.valueOf(expectedResult)
    );
  }
}