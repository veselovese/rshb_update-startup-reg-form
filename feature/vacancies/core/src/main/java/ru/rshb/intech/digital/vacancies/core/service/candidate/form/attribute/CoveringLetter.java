package ru.rshb.intech.digital.vacancies.core.service.candidate.form.attribute;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.text.TextAttributeBase;
import ru.rshb.intech.digital.vacancies.core.service.candidate.form.VacancyCandidateForm;
import ru.rshb.intech.digital.vacancies.entity.CandidateCv;
import ru.rshb.intech.digital.vacancies.model.CandidateCvDto;

@Component("vacancyCandidateCvFormCovLetter")
@FormAttribute(VacancyCandidateForm.class)
public class CoveringLetter extends TextAttributeBase<CandidateCvDto, CandidateCv> {

  public static final String TITLE_KEY = "vacancies.candidate.cv.form.attr.cov-letter";
  private static final int MAX_LENGTH = 1000;

  public CoveringLetter() {
    super(TITLE_KEY, MAX_LENGTH);
  }

  @Override
  public String supplyInputValue(CandidateCvDto inputData) {
    return inputData.getCoveringLetter();
  }

  @Override
  public void consumeOutputValue(String outputValue, CandidateCv outputData) {
    outputData.setCoveringLetter(outputValue);
  }
}
