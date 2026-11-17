package ru.rshb.intech.digital.feature.internship.core.service.registration.form.attribute;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.censor.forms.CensorAnalyzer;
import ru.rshb.intech.digital.commons.forms.attributes.AttributeParams;
import ru.rshb.intech.digital.commons.forms.attributes.BusinessRulesValidator;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheck;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckAnalyzer;
import ru.rshb.intech.digital.commons.forms.attributes.PreCheckResult;
import ru.rshb.intech.digital.commons.forms.attributes.ValueMapper;
import ru.rshb.intech.digital.commons.forms.context.FormContext;
import ru.rshb.intech.digital.commons.forms.exception.AttributeBusinessRulesException;
import ru.rshb.intech.digital.feature.internship.core.dto.InternshipRegistrationDto;
import ru.rshb.intech.digital.feature.internship.core.entity.Registration;
import ru.rshb.intech.digital.feature.internship.core.exception.RegistrationValidationException;
import ru.rshb.intech.digital.feature.internship.core.service.registration.form.InternshipRegistrationForm;
import ru.rshb.intech.digital.messages.I18n;
import ru.rshb.intech.digital.references.universities.core.repository.UniversityRepository;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.left;
import static org.apache.commons.lang3.StringUtils.trim;

@Component("internshipRegistrationUniversity")
@FormAttribute(InternshipRegistrationForm.class)
public class University extends InternshipRegistrationAttribute<University.UniversityInfo, University.UniversityInfo> {

  public static final String TITLE_KEY = "internship.registration.form.field.education.university";


  public University(@Nonnull UniversityRepository universityRepository, @Nonnull CensorAnalyzer censorAnalyzer) {
    super(
        AttributeParams.Builder.<InternshipRegistrationDto, University.UniversityInfo, Registration, University.UniversityInfo>create()
            .name(I18n.of(TITLE_KEY))
            .nullable(true)
            .preCheck(new PreCheck<>(new UniversityPreCheck(censorAnalyzer)))
            .valueMapper(new UniversityMapper())
            .businessRulesValidator(new UniversityValidator(universityRepository))
            .build()
    );
  }


  @Override
  public UniversityInfo supplyInputValue(@Nonnull InternshipRegistrationDto inputData) {
    return new UniversityInfo(inputData.getUniversityId(), inputData.getUniversityCustom());
  }

  @Override
  public void consumeOutputValue(UniversityInfo outputValue, @Nonnull Registration outputData) {
    outputData.setUniversityId(outputValue.getId());
    outputData.setUniversityCustom(outputValue.getName());
  }

  @RequiredArgsConstructor
  private static class UniversityPreCheck implements PreCheckAnalyzer<University.UniversityInfo> {

    private final CensorAnalyzer censorAnalyzer;

    @Nonnull
    @Override
    public PreCheckResult analyze(University.UniversityInfo value) {
      return censorAnalyzer.analyze(value.getName());
    }
  }

  @Getter
  @Setter
  @AllArgsConstructor
  public static class UniversityInfo {
    private String id;
    private String name;
  }

  private static class UniversityMapper implements ValueMapper<UniversityInfo, UniversityInfo> {

    @Override
    public <D, E> UniversityInfo map(UniversityInfo value, @Nonnull FormContext<D, E> context) {
      final var id = left(trim(value.getId()), Registration.UNIVERSITY_MAX_LEN);
      final var name = left(trim(value.getName()), Registration.UNIVERSITY_MAX_LEN);
      return new UniversityInfo(id, name);
    }
  }

  @RequiredArgsConstructor
  private static class UniversityValidator
      implements BusinessRulesValidator<InternshipRegistrationDto, Registration, UniversityInfo> {

    private final UniversityRepository universityRepository;

    @Override
    public void validate(UniversityInfo value,
                         @Nonnull FormContext<InternshipRegistrationDto, Registration> context)
        throws AttributeBusinessRulesException {

      if (isNotBlank(value.getName())) {
        value.setId(null);
        return;
      }

      if (isNotBlank(value.getId())) {

        if (!universityRepository.existsByExternalId(value.getId())) {
          throw RegistrationValidationException.invalidUniversityId();
        }

        value.setName(null);
        return;
      }

      value.setId(null);
      value.setName(null);
    }
  }
}
