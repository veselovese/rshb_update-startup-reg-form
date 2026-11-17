package ru.rshb.intech.digital.feature.startups.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StartupRegistrationDtoTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void validatesAllRequiredFieldsAndAllowsOptionalFieldsToBeOmitted() {
    assertTrue(validator.validate(validRegistration()).isEmpty());
  }

  @Test
  void rejectsUnknownRevenueAndVideoHosts() {
    final var registration = validRegistration();
    registration.setRevenueRange("100-200 млн");
    registration.setVideoUrl("https://example.com/project");

    final var invalidFields = validator.validate(registration).stream()
        .map(error -> error.getPropertyPath().toString())
        .collect(Collectors.toSet());

    assertEquals(Set.of("revenueRange", "videoUrl"), invalidFields);
  }

  @Test
  void requiresExactlyTheTwelveMandatoryFields() {
    final var missingFields = validator.validate(new StartupRegistrationDto()).stream()
        .map(error -> error.getPropertyPath().toString())
        .collect(Collectors.toSet());

    assertEquals(
        Set.of(
            "applicantName",
            "email",
            "phone",
            "city",
            "projectName",
            "foundedYear",
            "descriptionShort",
            "descriptionFull",
            "revenueRange",
            "categoryIds",
            "projectStageId",
            "hasAiSolutions"
        ),
        missingFields
    );
  }

  private StartupRegistrationDto validRegistration() {
    final var registration = new StartupRegistrationDto();
    registration.setApplicantName("Иван Иванов");
    registration.setEmail("ivan@example.com");
    registration.setPhone("+7 (999) 666-42-42");
    registration.setCity("Москва");
    registration.setProjectName("Агропроект");
    registration.setFoundedYear(2024);
    registration.setDescriptionShort("Краткое описание");
    registration.setDescriptionFull("Полное описание проекта");
    registration.setRevenueRange("До 1 млн");
    registration.setCategoryIds(List.of("category-id"));
    registration.setProjectStageId("stage-id");
    registration.setHasAiSolutions(false);
    return registration;
  }
}
