package ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.DataFormatValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBadFormatException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.commons.forms.exception.AttributeValueMissingException;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.exception.RegistrationValidationException;
import ru.rshb.intech.digital.feature.internship.core.repository.education.EducationStageRepository;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.InternshipRegistrationForm;
import ru.rshb.intech.digital.messages.I18n;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.left;
import static org.apache.commons.lang3.StringUtils.trim;

/**
 * Атрибут "Ступень обучения".
 * Форма подачи заявки на стажировку.
 */
@Component("internshipRegistrationEducationStage")
@FormAttribute(InternshipRegistrationForm.class)
public class EducationStage
    extends InternshipRegistrationAttribute<EducationStage.EducationStageInfo, EducationStage.EducationStageInfo> {

  public static final String TITLE_KEY = "internship.registration.form.field.education.stage";

  public EducationStage(@Nonnull EducationStageRepository educationStageRepository) {
    super(
        AttributeParams.Builder.<InternshipRegistrationDto, EducationStage.EducationStageInfo, Registration, EducationStage.EducationStageInfo>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(true)
            .dataFormatValidator(new EducationStageDataFormatValidator<>())
            .valueMapper(new EducationStageMapper())
            .businessRulesValidator(new EducationStageValidator(educationStageRepository))
            .build()
    );
  }

  @Override
  public EducationStageInfo supplyInputValue(@Nonnull InternshipRegistrationDto inputData) {
    return new EducationStageInfo(inputData.getEducationStage(), inputData.getEducationStageYear());
  }

  @Override
  public void consumeOutputValue(EducationStageInfo outputValue, @Nonnull Registration outputData) {
    outputData.setEducationStage(outputValue.getStage());
    outputData.setEducationYear(outputValue.getYear());
  }


  private static class EducationStageDataFormatValidator<D, E> implements DataFormatValidator<D, EducationStageInfo, E> {

    private static final int MIN_YEAR = 0;

    @Override
    public <A extends AttributeParams<D, EducationStageInfo, E, ?>> void validate(EducationStageInfo value,
                                                                                  @Nonnull A attrProps,
                                                                                  @Nonnull FormContext<D, E> context)
        throws AttributeValueMissingException, AttributeBadFormatException {

      final var year = defaultIfNull(value.getYear(), MIN_YEAR);
      if (year < MIN_YEAR) {
        throw RegistrationValidationException.invalidEducationYearFormat();
      }
    }
  }

  private static class EducationStageMapper implements ValueMapper<EducationStageInfo, EducationStageInfo> {

    @Override
    public <D, E> EducationStageInfo map(EducationStageInfo value, @Nonnull FormContext<D, E> context) {
      final var stage = left(trim(value.getStage()), Registration.EDU_STAGE_MAX_LEN);
      final var year = value.getYear();
      return new EducationStageInfo(stage, year);
    }
  }

  @RequiredArgsConstructor
  private static class EducationStageValidator
      implements BusinessRulesValidator<InternshipRegistrationDto, Registration, EducationStageInfo> {

    private final EducationStageRepository educationStageRepository;

    @Override
    public void validate(EducationStageInfo eduStageInfo,
                         @Nonnull FormContext<InternshipRegistrationDto, Registration> context)
        throws AttributeBusinessRulesException {

      if (isBlank(eduStageInfo.getStage())) {
        eduStageInfo.setStage(null);
        eduStageInfo.setYear(null);
        return;
      }

      final var stage = educationStageRepository.findByTitleIgnoreCase(eduStageInfo.getStage())
          .orElseThrow(RegistrationValidationException::invalidEducationLevel);

      if (stage.isWithoutYear()) {
        eduStageInfo.setYear(null);
        return;
      }

      final var year = eduStageInfo.getYear();
      if (isYearOutOfInterval(year, stage.getMinYear(), stage.getMaxYear())) {
        throw RegistrationValidationException.invalidEducationYear();
      }
    }

    private boolean isYearOutOfInterval(@Nonnull Integer year,
                                        @Nonnull Integer minYear,
                                        @Nonnull Integer maxYear) {
      return (year < minYear || year > maxYear);
    }
  }

  @Getter
  @Setter
  @AllArgsConstructor
  public static class EducationStageInfo {
    private String stage;
    private Integer year;
  }
}
