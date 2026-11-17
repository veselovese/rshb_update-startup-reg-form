package ru.rshb.intech.digital.vacancies.core.service.candidate.form.attribute;

import org.springframework.stereotype.Component;
import ru.rshb.intech.digital.commons.forms.attributes.FormAttribute;
import ru.rshb.intech.digital.commons.forms.attributes.applicant.ApplicantNameAttributeBase;
import ru.rshb.intech.digital.vacancies.core.service.candidate.form.VacancyCandidateForm;
import ru.rshb.intech.digital.vacancies.entity.CandidateCv;
import ru.rshb.intech.digital.vacancies.model.CandidateCvDto;

@Component("vacancyCandidateCvFormName")
@FormAttribute(VacancyCandidateForm.class)
public class CandidateName extends ApplicantNameAttributeBase<CandidateCvDto, CandidateCv> {

  @Override
  public String supplyInputValue(CandidateCvDto inputData) {
    return inputData.getCandidateName();
  }

  @Override
  public void consumeOutputValue(String outputValue, CandidateCv outputData) {
    outputData.setCandidateName(outputValue);
  }
}
